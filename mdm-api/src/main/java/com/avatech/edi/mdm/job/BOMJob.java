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
                for (TaskRecord taskRecord : taskRecordList) {
                    try {
                        billOfMaterial = billOfMaterialService.fetchBillOfMaterial(taskRecord.getCompanyName(), taskRecord.getDocEntry());
                        if (billOfMaterial == null)
                            throw new Exception("BOM[" + taskRecord.getCompanyName() + "_" + taskRecord.getDocEntry() + "]未找到");
                        billOfMaterialService.processApprovedResult(billOfMaterial);
                    } catch (Exception e) {
                        logger.error("[" + taskRecord.getCompanyName() + "_" + taskRecord.getDocEntry() + taskRecord.getDocEntry() + "]BOM处理发生异常", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("处理BOM信息异常", e);
        }
    }

}
