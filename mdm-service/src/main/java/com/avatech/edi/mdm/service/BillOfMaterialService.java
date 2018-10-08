package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.businessone.BOM.B1BillOfMaterialService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Manager;
import com.avatech.edi.mdm.dto.SyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillOfMaterialService {

    private final String NO_COMPANY = "无效的公司名称";

    @Autowired
    private B1Manager b1Manager;

    @Autowired
    private B1BillOfMaterialService b1BillOfMaterialService;

    public SyncResult syncBOM(IBillOfMaterial billOfMaterial){
        if(billOfMaterial.getCompanyName().isEmpty()){
            return SyncResult.error(billOfMaterial.getDocEntry().toString(),NO_COMPANY);
        }
        B1Connection connection = b1Manager.getB1ConnInstance(billOfMaterial.getCompanyName());
        if(connection == null)
            return SyncResult.error(billOfMaterial.getDocEntry().toString(),NO_COMPANY);
        String docEntry = b1BillOfMaterialService.syncBillOfMaterial(billOfMaterial,connection);
        return SyncResult.ok(docEntry);
    }
}
