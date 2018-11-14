package com.avatech.edi.mdm.businessone.project;

import com.avatech.edi.mdm.bo.IProjectBudget;
import com.avatech.edi.mdm.bo.IProjectBudgetItem;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.businessone.approval.B1ApprovalTempleService;
import com.avatech.edi.mdm.businessone.config.B1Data;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class B1ProjectBudgetServiceImp implements B1ProjectBudgetService {

    private final Logger logger = LoggerFactory.getLogger(B1ProjectBudgetServiceImp.class);


    private final String BASE_TYPE = "U_BaseType";
    private final String BASE_DOCENTRY = "U_BaseEntry";
    private final String BASE_LINENUM = "U_BaseLineNum";
    private final String CREATOR = "U_Creator";

    private final String PROJECT_ID = "U_PrjCode";
    private final String PROJECT_NAME = "U_PrjName";
    private final String PROJECT_STAGE_CODE = "U_StageCode";
    private final String PROJECT_STAGE_NAME = "U_StageName";
    private final String ACTIVITY_TYPE = "U_ActType";
    private final String CARDCODE = "U_CardCode";
    private final String SUBJECT = "U_SrvcSbjct";
    private final String CONTENT = "U_SrvcCntnt";
    private final String ATLSTD = "U_AtlStd";
    private final String ATLSTT = "U_AtlStt";
    private final String ATLFND = "U_AtlFnd";
    private final String ATLFNT = "U_AtlFnt";
    private final String ATLDUR = "U_AtlDur";
    private final String ATLDURUN = "U_AtlDurUn";

    private final String OBJECT_CODE = "AVA_PM_TIMEBUDGET";

    @Autowired
    private B1ApprovalTempleService b1ApprovalTempleService;

    @Override
    public String syncProjectBudget(IProjectBudget projectBudget, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        int tempCode = -1;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();
            tempCode = b1ApprovalTempleService.getApproveTemple(B1Data.TRANSFER_REQUEST,company,projectBudget);
            if(tempCode > 0){
                b1ApprovalTempleService.inActiveApproveTemple(company);
                b1ApprovalTempleService.activeApproveTemple(true,tempCode,company);
            }
            IStockTransfer document = SBOCOMUtil.newStockTransfer(company,SBOCOMConstants.BoObjectTypes_StockTransfer_oInventoryTransferRequest);
            document.setCardCode(B1Data.VISUAL_SUPPLIER);
            document.setDocDate(new Date());
            document.setTaxDate(new Date());
            if(projectBudget.getRemarks() != null)
                document.setComments(projectBudget.getRemarks());
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            if(projectBudget.getPrjCode() != null)
                document.getUserFields().getFields().item(PROJECT_ID).setValue(projectBudget.getPrjCode().toString());
            if(projectBudget.getPrjName() != null)
                document.getUserFields().getFields().item(PROJECT_NAME).setValue(projectBudget.getPrjName());
            document.getUserFields().getFields().item(CREATOR).setValue(projectBudget.getCreator().toString());
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(projectBudget.getDocEntry().toString());
            for (IProjectBudgetItem item:projectBudget.getProjectBudgetItemList()) {
                document.getLines().setItemCode(B1Data.VISUAL_ITEMCODE);
                document.getLines().setWarehouseCode(B1Data.VISUAL_WHSCODE1);
                document.getLines().setFromWarehouseCode(B1Data.VISUAL_WHSCODE2);
                document.getLines().setQuantity(0.01);
                document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry().toString());
                document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineNum().toString());
                if(item.getPrjCode() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_ID).setValue(item.getPrjCode());
                if(item.getPrjName() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_NAME).setValue(item.getPrjName());
                if(item.getStageCode() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_STAGE_CODE).setValue(item.getStageCode());
                if(item.getStageName() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_STAGE_NAME).setValue(item.getStageName());
                if(item.getCardCode() != null)
                    document.getLines().getUserFields().getFields().item(CARDCODE).setValue(item.getCardCode());
                if(item.getSrvcSbjct() != null)
                    document.getLines().getUserFields().getFields().item(SUBJECT).setValue(item.getSrvcSbjct());
                if(item.getSrvcCntnt() != null)
                    document.getLines().getUserFields().getFields().item(CONTENT).setValue(item.getSrvcCntnt());
//               // if(item.getActType() != null)
//                  //  document.getLines().getUserFields().getFields().item(ACTIVITY_TYPE).setValue(item.getActType());
                if(item.getAtlStd() != null)
                    document.getLines().getUserFields().getFields().item(ATLSTD).setValue(item.getAtlStd());
                if(item.getAtlStt() != null)
                    document.getLines().getUserFields().getFields().item(ATLSTT).setValue(item.getAtlStt().toString());
                if(item.getAtlFnd() != null)
                    document.getLines().getUserFields().getFields().item(ATLFND).setValue(item.getAtlFnd());
                if(item.getAtlFnt() != null)
                    document.getLines().getUserFields().getFields().item(ATLFNT).setValue(item.getAtlFnt().toString());
                if(item.getAtlDur() != null)
                    document.getLines().getUserFields().getFields().item(ATLDUR).setValue(item.getAtlDur().toString());
                if(item.getAtlDurUn() != null)
                    document.getLines().getUserFields().getFields().item(ATLDURUN).setValue(item.getAtlDurUn());
                document.getLines().add();
            }
            int rt = document.add();
            if(rt == 0 )
                return company.getNewObjectKey();
            else throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
        }catch (SBOCOMException e){
            logger.error("同步项目预算发生异常",e);
            throw new B1Exception(e);
        }finally {
            if(company != null){
                if(tempCode > 0){
                    b1ApprovalTempleService.inActiveApproveTemple(company);
                }
                company.disconnect();
            }
        }
    }
}
