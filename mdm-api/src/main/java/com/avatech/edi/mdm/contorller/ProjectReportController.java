package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.ProjectReport;
import com.avatech.edi.mdm.config.ServiceException;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.ProjectReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("projectreport/v1/*")
public class ProjectReportController {

    /**
     * 定义资源总数
     */
    Semaphore semaphore=new Semaphore(1);

    private final Logger logger = LoggerFactory.getLogger(ProjectReportController.class);

    @Autowired
    private ProjectReportService projectReportService;

    @RequestMapping(value = "report",method ={RequestMethod.POST})
    public @ResponseBody
    Result postProjectReport( //@RequestParam(ServiceParam.TOKEN) String token,
                              @RequestBody ProjectReport report) {
        Result rt;
        int availablePermits=semaphore.availablePermits();//可用资源数
        if(availablePermits<=0){
            logger.info("项目汇报审批资源已被占用");
            return Result.error("1100","项目汇报审批资源正在被使用，请稍后再试");
        }
        try
        {
            semaphore.acquire(1);  //请求占用一个资源
            logger.info("接收项目工时汇报信息》》》》》"+ report.toString());
            if(report.getProject() == null || report.getProject().isEmpty()){
                rt = Result.error("1001","项目信息为空");
            }else {
                SyncResult results = projectReportService.syncProjectReport(report);
                rt = Result.ok(results);
            }
        }catch (ServiceException e){
            logger.error(report.toString(),e);
            rt = Result.error("-1",e);
        }
        catch (Exception e){
            logger.error(report.toString(),e);
            rt = Result.error("-1",e);
        }finally {
            semaphore.release(1);//释放一个资源
        }
        logger.info("项目工时汇报推送返回信息》》》》》"+ rt.toString());
        return rt;
    }

}
