package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.ProjectBudget;
import com.avatech.edi.mdm.bo.ProjectBudgetItem;
import com.avatech.edi.mdm.bo.ProjectReport;
import com.avatech.edi.mdm.bo.ProjectReportItem;
import com.avatech.edi.mdm.config.ServiceException;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.ProjectBudgetService;
import com.avatech.edi.mdm.service.ProjectReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("project/v1/*")
public class ProjectController {

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectBudgetService projectBudgetService;

    @Autowired
    private ProjectReportService projectReportService;

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

    @GetMapping("reports")
    public ProjectReport getProjectReport(){
        ProjectReport projectReport = new ProjectReport();
        ProjectReportItem projectReportItem;
        projectReport.setbPLId(1);
        projectReport.setDeptId("D11");
        projectReport.setDocEntry(1);
        projectReportItem = new ProjectReportItem();
        projectReportItem.setDocEntry(1);
        projectReportItem.setLineNum(1);
        projectReport.getProjectReportItems().add(projectReportItem);
        return projectReport;
    }

    @RequestMapping(value = "budget",method ={RequestMethod.POST})
    public @ResponseBody Result postProjectBudget( //@RequestParam(ServiceParam.TOKEN) String token,
                           @RequestBody ProjectBudget budget) {
        Result rt;
        try
        {
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
        }
        logger.info("项目预算推送返回信息》》》》》"+ rt.toString());
        return rt;
    }

    @RequestMapping(value = "report",method ={RequestMethod.POST})
    public @ResponseBody Result postProjectReport( //@RequestParam(ServiceParam.TOKEN) String token,
                                                   @RequestBody ProjectReport report) {
        Result rt;
        try
        {
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
        }
        logger.info("项目工时汇报推送返回信息》》》》》"+ rt.toString());
        return rt;
    }
}
