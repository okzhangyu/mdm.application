package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.ICashFlow;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 现金流生成服务
 */
@Component
public class B1CashFlowServiceImp implements B1CashFlowService {

    @Override
    public String syncCashFlow(ICashFlow cashFlow, B1Connection b1Connection,List<DataTemple> dataTempleList){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            ICompanyService companyService = company.getCompanyService();
            //SBOCOMUtil.cash
            ICashFlowLineItemsService cashFlowLineItemsService = SBOCOMUtil.newCashFlowLineItemsService(companyService);
            ICashFlowLineItemParams cashFlowLineItemParams = (ICashFlowLineItemParams) cashFlowLineItemsService
                    .getDataInterface(SBOCOMConstants.CashFlowLineItemsServiceDataInterfaces_cflisCashFlowLineItem);
            //cashFlowLineItemsService.
            cashFlowLineItemParams.setLineItemID(cashFlow.getCashFlowCode());
            ICashFlowLineItem cashFlowLineItem = cashFlowLineItemsService.getCashFlowLineItem(cashFlowLineItemParams);

            CashFlowLineItem cashFlowLineItem1 = (CashFlowLineItem)cashFlowLineItemsService
                    .getDataInterface(SBOCOMConstants.CashFlowLineItemsServiceDataInterfaces_cflisCashFlowLineItem);
            //cashFlowLineItem1
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
