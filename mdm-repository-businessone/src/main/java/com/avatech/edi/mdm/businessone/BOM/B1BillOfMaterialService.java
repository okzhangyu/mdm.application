package com.avatech.edi.mdm.businessone.BOM;

import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.dto.SyncResult;

public interface B1BillOfMaterialService {

    String syncBillOfMaterial(IBillOfMaterial billOfMaterial, B1Connection b1Connection);
}
