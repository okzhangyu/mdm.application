package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IProjectReport;
import com.avatech.edi.mdm.businessone.project.B1ProjectReportService;
import com.avatech.edi.mdm.businessone.project.B1ProjectReportService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.B1Manager;
import com.avatech.edi.mdm.dto.SyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectReportService {

    @Autowired
    private B1ProjectReportService b1ProjectReportService;

    @Autowired
    private B1Manager b1Manager;

    public SyncResult syncProjectReport(IProjectReport projectReport){
        if(projectReport.getCompanyDB().isEmpty()){
            return SyncResult.error(projectReport.getDocEntry().toString(), B1Data.NO_COMPANY);
        }
        B1Connection connection = b1Manager.getB1ConnInstance(projectReport.getCompanyDB());
        if(connection == null)
            return SyncResult.error(projectReport.getDocEntry().toString(),B1Data.NO_COMPANY);
        String docEntry = b1ProjectReportService.syncProjectReport(projectReport,connection);
        return SyncResult.ok(projectReport.getDocEntry().toString(),docEntry);
    }
}
