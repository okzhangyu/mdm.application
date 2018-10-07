package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.dto.SyncResult;
import org.springframework.stereotype.Service;

@Service
public class BillOfMaterialService {

    public SyncResult syncBOM(IBillOfMaterial billOfMaterial){
        SyncResult syncResult = new SyncResult();
        return syncResult;
    }
}
