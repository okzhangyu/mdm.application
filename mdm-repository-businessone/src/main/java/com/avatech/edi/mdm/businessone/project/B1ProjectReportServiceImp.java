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

import java.util.Date;

@Component
public class B1ProjectReportServiceImp implements B1ProjectReportService {

    private final Logger logger = LoggerFactory.getLogger(B1ProjectReportServiceImp.class);

    private final String BASE_TYPE = "U_BaseType";
    private final String BASE_DOCENTRY = "U_BaseDocEntry";
    private final String BASE_LINENUM = "U_BaseLineNum";

    private final String OBJECT_CODE = "AVA_PM_ACTIVITY";

    @Override
    public String syncProjectReport(IProjectReport projectReport, B1Connection b1Connection) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IStockTransfer document = SBOCOMUtil.newStockTransfer(company, SBOCOMConstants.BoObjectTypes_oStockTransfer);
            document.setCardCode(B1Data.VISUAL_CARDCODE);
            document.setDocDate(new Date());
            document.setTaxDate(new Date());
            if(projectReport.getRemarks() != null)
                document.setComments(projectReport.getRemarks());
            document.getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
            document.getUserFields().getFields().item(BASE_DOCENTRY).setValue(projectReport.getDocEntry());

            for (IProjectReportItem item:projectReport.getProjectReportItems()) {
                document.getLines().setItemCode(B1Data.VISUAL_ITEMCODE);
                document.getLines().setWarehouseCode(B1Data.VISUAL_WHSCODE1);
                document.getLines().setFromWarehouseCode(B1Data.VISUAL_WHSCODE2);

                document.getLines().getUserFields().getFields().item(BASE_TYPE).setValue(OBJECT_CODE);
                document.getLines().getUserFields().getFields().item(BASE_DOCENTRY).setValue(item.getDocEntry());
                document.getLines().getUserFields().getFields().item(BASE_LINENUM).setValue(item.getLineNum());
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
