package com.avatech.edi.businessone.masterdata;

import com.avatech.edi.businessone.B1Exception;
import com.avatech.edi.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.ICashFlow;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.*;

/**
 * 现金流生成服务
 */
public class CashFlowService {

    public String syncCashFlow(ICashFlow cashFlow, B1Connection b1Connection){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            ICompanyService companyService = company.getCompanyService();
            ICashFlowLineItemsService cashFlowLineItemsService = SBOCOMUtil.newCashFlowLineItemsService(companyService);
            //cashFlowLineItemsService.

            //IProfitCentersService profitCentersService = SBOCOMUtil.newProfitCentersService(company);

        }catch (SBOCOMException e){
         throw new B1Exception(e);
        }catch (Exception e){
            throw e;
        }
        return null;
    }
}
