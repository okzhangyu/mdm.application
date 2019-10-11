package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.bo.Material;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class B1MaterialServiceImp implements B1BusinessMaterialService {

    private final Logger logger = LoggerFactory.getLogger(B1BusinessMaterialService.class);
    @Override
    public String syncMaterial(B1Connection connection, Material material, List<DataTemple> dataTemples) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(connection);
            company = boRepositoryBusinessOne.getCompany();
            IItems items = SBOCOMUtil.newItems(company);
            Boolean isExist;
            if (items.getByKey(material.getItemCode())){
                  isExist =true;
            }else {
                isExist = false;
            }
            items.setItemCode(material.getItemCode());
            items.setItemName(material.getItemName());
            items.setSerialNum(material.getSerialNo());
            items.setItemsGroupCode(1);
            for (DataTemple dataTemple:dataTemples){
                if (dataTemple.getFieldName().isEmpty()||dataTemple.getFieldName()==null){
                    continue;
                }
            }
            int rstCode;
            if (isExist){
               rstCode = items.update();
            }else {
               rstCode = items.add();
            }
            if (rstCode==0){
                return company.getNewObjectCode();
            }else {
                throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
            }
        }catch (Exception e){
            logger.info("同步物料主数据失败",e.getMessage());
            throw new B1Exception(e);
        }
    }
}
