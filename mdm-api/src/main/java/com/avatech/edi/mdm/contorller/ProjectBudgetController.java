package com.avatech.edi.mdm.contorller;


import com.avatech.edi.mdm.bo.ProjectBudget;
import com.avatech.edi.mdm.bo.ProjectBudgetItem;
import com.avatech.edi.mdm.config.ServiceException;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.ProjectBudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("projectbudget/v1/*")
public class ProjectBudgetController {
    private final Logger logger = LoggerFactory.getLogger(ProjectBudgetController.class);

    @Autowired
    private ProjectBudgetService projectBudgetService;

    /**
     * 定义资源总数
     */
    Semaphore semaphore=new Semaphore(1);

    @GetMapping("budgets")
    public ProjectBudget getProjectBudget(){
        ProjectBudget projectBudget = new ProjectBudget();
        ProjectBudgetItem projectBudgetItem;
        projectBudget.setCardCode("C1001");
        projectBudget.setDeptId("D11");
        projectBudget.setPrjCode("自动化");
        projectBudgetItem = new ProjectBudgetItem();
        projectBudgetItem.setDocEntry(1);
        projectBudgetItem.setLineNum(1);
        projectBudget.getProjectBudgetItemList().add(projectBudgetItem);
        return projectBudget;
    }

    @RequestMapping(value = "budget",method ={RequestMethod.POST})
    public @ResponseBody
    Result postProjectBudget( //@RequestParam(ServiceParam.TOKEN) String token,
                              @RequestBody ProjectBudget budget) {
        Result rt;
        int availablePermits=semaphore.availablePermits();//可用资源数
        if(availablePermits<=0){
            logger.info("项目预算审批资源已被占用");
            return Result.error("1100","项目预算审批资源正在被使用，请稍后再试");
        }
        try
        {
            semaphore.acquire(1);  //请求占用一个资源
            logger.info("接收项目预算信息》》》》》"+ budget.toString());
            if(budget.getPrjCode() == null || budget.getPrjCode().isEmpty())
                rt = Result.error("1001","项目信息为空");
            else {
                SyncResult results = projectBudgetService.syncProjectBudget(budget);
                rt = Result.ok(results);
            }
        }catch (ServiceException e){
            logger.error(budget.toString(),e);
            rt = Result.error("-1",e);
        }
        catch (Exception e){
            logger.error(budget.toString(),e);
            rt = Result.error("-1",e);
        }finally {
            semaphore.release(1);//释放一个资源
        }
        logger.info("项目预算推送返回信息》》》》》"+ rt.toString());
        return rt;
    }

}
