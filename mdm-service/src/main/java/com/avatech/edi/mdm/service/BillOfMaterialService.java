package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.BillOfMaterial;
import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.businessone.bom.B1BillOfMaterialService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.B1Manager;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryBillOfMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillOfMaterialService {

    private final Logger logger = LoggerFactory.getLogger(BillOfMaterialService.class);

    @Autowired
    private IBORepositoryBillOfMaterial repositoryBillOfMaterial;

    @Autowired
    private B1Manager b1Manager;

    @Autowired
    private B1BillOfMaterialService b1BillOfMaterialService;

    public SyncResult syncBOM(IBillOfMaterial billOfMaterial){
        if(billOfMaterial.getCompanyDB().isEmpty()){
            return SyncResult.error(billOfMaterial.getDocEntry().toString(), B1Data.NO_COMPANY);
        }
        B1Connection connection = b1Manager.getB1ConnInstance(billOfMaterial.getCompanyDB());
        if(connection == null)
            return SyncResult.error(billOfMaterial.getDocEntry().toString(),B1Data.NO_COMPANY);
        String docEntry = b1BillOfMaterialService.syncBillOfMaterial(billOfMaterial,connection);
        return SyncResult.ok(billOfMaterial.getDocEntry().toString(),docEntry);
    }

    public void processApprovedResult(IBillOfMaterial billOfMaterial){
        if(billOfMaterial.getCompanyDB().isEmpty()){
            //return SyncResult.error(billOfMaterial.getDocEntry().toString(), B1Data.NO_COMPANY);
            return;
        }
        B1Connection connection = b1Manager.getB1ConnInstance(billOfMaterial.getCompanyDB());
        if(connection == null)
            return;// SyncResult.error(billOfMaterial.getDocEntry().toString(),B1Data.NO_COMPANY);
        this.b1BillOfMaterialService.approvedBillOfMaterial(billOfMaterial,connection);
    }

    /**
     * 查询BOM信息
     * @param companDB 账套名称
     * @param docEntry 单据号
     * @return
     */
    public BillOfMaterial fetchBillOfMaterial(String companDB,String docEntry){
        Optional<BillOfMaterial> billOfMaterial = this.repositoryBillOfMaterial.findById(companDB+"_"+docEntry);
        return billOfMaterial.get();
    }
}
