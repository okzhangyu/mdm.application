package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IProjectBudget;
import com.avatech.edi.mdm.bo.IProjectReport;
import com.avatech.edi.mdm.businessone.project.B1ProjectBudgetService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.B1Manager;
import com.avatech.edi.mdm.dto.SyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectBudgetService {

    @Autowired
    private B1Manager b1Manager;

    @Autowired
    private B1ProjectBudgetService b1ProjectBudgetService;

    public SyncResult syncProjectBudget(IProjectBudget projectBudget){
        if(projectBudget.getCompanyDB().isEmpty()){
            return SyncResult.error(projectBudget.getDocEntry().toString(), B1Data.NO_COMPANY);
        }
        B1Connection connection = b1Manager.getB1ConnInstance(projectBudget.getCompanyDB());
        if(connection == null)
            return SyncResult.error(projectBudget.getDocEntry().toString(),B1Data.NO_COMPANY);
        String docEntry = b1ProjectBudgetService.syncProjectBudget(projectBudget,connection);
        return SyncResult.ok(projectBudget.getDocEntry().toString(),docEntry);
    }
}
