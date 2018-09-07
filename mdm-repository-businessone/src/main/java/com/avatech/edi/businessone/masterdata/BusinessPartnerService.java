package com.avatech.edi.businessone.masterdata;

import com.avatech.edi.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IBusinessPartner;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.ICompany;

public class BusinessPartnerService {

    public String syncBusinessPartner(IBusinessPartner businessPartner, B1Connection b1Connection){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            // SBOCOMUtil.
            //IProfitCentersService profitCentersService = SBOCOMUtil.newProfitCentersService(company);

        }catch (Exception e){
            throw e;
        }
        return null;
    }
}
