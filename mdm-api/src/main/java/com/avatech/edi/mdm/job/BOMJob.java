package com.avatech.edi.mdm.job;

import com.avatech.edi.mdm.bo.BillOfMaterial;
import com.avatech.edi.mdm.bo.CompontOfMaterialListItem;
import com.avatech.edi.mdm.config.BusinessObjectType;
import com.avatech.edi.mdm.config.BusinessType;
import com.avatech.edi.mdm.service.BillOfMaterialService;
import com.avatech.edi.mdm.service.TaskRecordService;
import com.avatech.edi.mdm.vo.TaskRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BOMJob {

    private final Logger logger = LoggerFactory.getLogger(BOMJob.class);

    @Autowired
    private BillOfMaterialService billOfMaterialService;

    @Autowired
    private TaskRecordService taskRecordService;

    @Scheduled(cron = "0 0/1 * * * ?")
    private void processData() {
        try {
            List<TaskRecord> taskRecordList = taskRecordService.fetchTaskRecord(BusinessObjectType.BILL_OF_MATERIAL);
            if (taskRecordList.size() > 0) {
                logger.info("获取任务清单信息：" + taskRecordList.toString());
                BillOfMaterial billOfMaterial;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (TaskRecord taskRecord : taskRecordList) {
                    try {
                        billOfMaterial = billOfMaterialService.fetchBillOfMaterial(taskRecord.getCompanyName(), taskRecord.getUniqueKey());
                        if (billOfMaterial == null)
                            throw new Exception("BOM[" + taskRecord.getCompanyName() + "_" + taskRecord.getDocEntry() + "]未找到");
                        billOfMaterialService.processApprovedResult(billOfMaterial);
                        taskRecord.setIsSync("Y");
                        taskRecord.setSyncMessage("Successful");
                        taskRecord.setSyncDate(formatter.format(new Date()));
                        taskRecordService.updateTask(taskRecord);
                    } catch (Exception e) {
                        taskRecord.setIsSync("E");
                        if(e.getMessage()!= null && e.getMessage().length() > 99)
                            taskRecord.setSyncMessage(e.getMessage().substring(0,99));
                        else
                            taskRecord.setSyncMessage(e.getMessage());
                        taskRecord.setSyncDate(formatter.format(new Date()));
                        taskRecordService.updateTask(taskRecord);
                        logger.error("[" + taskRecord.getCompanyName() + "_" + taskRecord.getDocEntry() + taskRecord.getDocEntry() + "]BOM处理发生异常", e);
                    }
                }
                logger.info("BOM审批生成生产订单完成");
            }
        } catch (Exception e) {
            logger.error("处理BOM信息异常", e);
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    private void handleTaskErrorData() {
        try {
            List<TaskRecord> errorTaskList = taskRecordService.fetchErrorTaskRecord();
            logger.info("获取中间表错误" + errorTaskList.size() + "条记录");
            for (TaskRecord taskRecord:errorTaskList) {
                taskRecord.setIsSync("N");
                taskRecord.setErrorTime(taskRecord.getErrorTime() + 1);
                taskRecordService.updateTask(taskRecord);
            }
        } catch (Exception e) {
            logger.error("处理中间表错误记录异常:", e);
        }
    }


}
