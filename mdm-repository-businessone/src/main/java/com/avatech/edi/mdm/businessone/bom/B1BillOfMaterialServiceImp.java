package com.avatech.edi.mdm.businessone.bom;

import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.bo.ICompontOfMaterialListItem;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.businessone.config.B1Data;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class B1BillOfMaterialServiceImp implements B1BillOfMaterialService {

    private final Logger logger = LoggerFactory.getLogger(B1BillOfMaterialServiceImp.class);

    private final String BASE_TYPE = "U_BaseType";
    private final String BASE_DOCENTRY = "U_BaseDocEntry";
    private final String BASE_LINENUM = "U_BaseLineNum";
    private final String BOM_ITEMCODE  = "U_ItemCode";
    private final String BOM_ITEMNAME = "U_ItemName";
    private final String BOM_TREETYPE = "U_TreeType";
    private final String BOM_UOM = "U_UOM";
    private final String BOM_UNITQTY = "U_UnitQty";
    private final String BOM_ACTIVTED = "U_Activated";
    private final String BOM_PROJECT = "U_Project";

    private final String OBJECT_CODE = "AVA_PM_BOMNew";


    @Override
    public String syncBillOfMaterial(IBillOfMaterial billOfMaterial, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IDocuments document = SBOCOMUtil.newDocuments(company, B1Data.PURCHASEQUOTE);
            document.setCardCode(billOfMaterial.getBPCode());
            document.setDocDate(new Date());
            document.setTaxDate(new Date());
            document.setVatDate(new Date());
            document.setRequriedDate(new Date());
            document.setComments(billOfMaterial.getRemarks());
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(billOfMaterial.getDocEntry());
            document.getUserFields().getFields().item(BOM_ITEMCODE).setValue(billOfMaterial.getItemCode());
            document.getUserFields().getFields().item(BOM_ITEMNAME).setValue(billOfMaterial.getItemName());
            document.getUserFields().getFields().item(BOM_TREETYPE).setValue(billOfMaterial.getTreeType());
            document.getUserFields().getFields().item(BOM_UOM).setValue(billOfMaterial.getUom());
            if(billOfMaterial.getProject() != null)
                document.getUserFields().getFields().item(BOM_PROJECT).setValue(billOfMaterial.getProject());
            if(billOfMaterial.getActived() != null)
                document.getUserFields().getFields().item(BOM_ACTIVTED).setValue(billOfMaterial.getActived());

            for (ICompontOfMaterialListItem item:billOfMaterial.getCompontOfMaterialListItems()) {
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry());
                document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineId());
                document.getLines().add();
            }
            int rt = document.add();
            if(rt == 0 )
                return company.getNewObjectKey();
            else throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
        }catch (SBOCOMException e){
            logger.error("同步BOM发生异常",e);
            throw new B1Exception(e);
        }
    }
}
