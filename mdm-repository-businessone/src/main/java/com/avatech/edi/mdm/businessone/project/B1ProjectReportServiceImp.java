package com.avatech.edi.mdm.businessone.project;

import com.avatech.edi.mdm.bo.IProjectReport;
import com.avatech.edi.mdm.bo.IProjectReportItem;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.businessone.config.B1Data;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class B1ProjectReportServiceImp implements B1ProjectReportService {

    private final Logger logger = LoggerFactory.getLogger(B1ProjectReportServiceImp.class);

    private final String BASE_TYPE = "U_BaseType";
    private final String BASE_DOCENTRY = "U_BaseEntry";
    private final String BASE_LINENUM = "U_BaseLineNum";
    private final String EMPLOYEE_NAME = "U_EmpName";
    private final String DEPATEMENT_NAME = "U_DeptName";
    private final String PROJECT_ID = "U_PrjCode";
    private final String PROJECT_NAME = "U_PrjName";
    private final String PROJECT_STAGE = "U_StageName";
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

    private final String OBJECT_CODE = "AVA_PM_ACTIVITY";

    @Override
    public String syncProjectReport(IProjectReport projectReport, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();
            IStockTransfer document = SBOCOMUtil.newStockTransfer(company, SBOCOMConstants.BoObjectTypes_oStockTransfer);
            document.setCardCode(B1Data.VISUAL_SUPPLIER);
            document.setDocDate(new Date());
            document.setTaxDate(new Date());
            if(projectReport.getRemarks() != null)
                document.setComments(projectReport.getRemarks());
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(projectReport.getDocEntry());
            if(projectReport.getEmpName() != null)
                document.getUserFields().getFields().item(EMPLOYEE_NAME).setValue(projectReport.getEmpName());
            if(projectReport.getDeptName() != null)
                document.getUserFields().getFields().item(DEPATEMENT_NAME).setValue(projectReport.getDeptName());

            for (IProjectReportItem item:projectReport.getProjectReportItems()) {
                document.getLines().setItemCode(B1Data.VISUAL_ITEMCODE);
                document.getLines().setWarehouseCode(B1Data.VISUAL_WHSCODE1);
                document.getLines().setFromWarehouseCode(B1Data.VISUAL_WHSCODE2);
                document.getLines().setQuantity(0.01);
                document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry());
                document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineNum());
                if(item.getPrjCode() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_ID).setValue(item.getPrjCode());
                if(item.getPrjName() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_NAME).setValue(item.getPrjName());
                if(item.getStageName() != null)
                    document.getLines().getUserFields().getFields().item(PROJECT_STAGE).setValue(item.getStageName());
                if(item.getCardCode() != null)
                    document.getLines().getUserFields().getFields().item(CARDCODE).setValue(item.getCardCode());
                if(item.getSrvcSbjct() != null)
                    document.getLines().getUserFields().getFields().item(SUBJECT).setValue(item.getSrvcSbjct());
                if(item.getSrvcCntnt() != null)
                    document.getLines().getUserFields().getFields().item(CONTENT).setValue(item.getSrvcCntnt());
                if(item.getActType() != null)
                    document.getLines().getUserFields().getFields().item(ACTIVITY_TYPE).setValue(item.getActType());
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
        }
    }
}
