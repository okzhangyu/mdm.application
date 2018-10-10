package com.avatech.edi.mdm.businessone.bom;

import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.config.B1Connection;

public interface B1BillOfMaterialService {

    String syncBillOfMaterial(IBillOfMaterial billOfMaterial, B1Connection b1Connection);
}
