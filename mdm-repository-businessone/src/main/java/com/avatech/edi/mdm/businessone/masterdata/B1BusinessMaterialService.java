package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.bo.Material;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;

import java.util.List;

public interface B1BusinessMaterialService {
    String syncMaterial(B1Connection connection, Material material, List<DataTemple>dataTemples);
}
