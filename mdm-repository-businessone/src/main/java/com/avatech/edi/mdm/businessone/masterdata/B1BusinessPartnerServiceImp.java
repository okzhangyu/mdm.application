package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IBusinessPartner;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.IBusinessPartners;
import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class B1BusinessPartnerServiceImp implements B1BusinessPartnerService {

    @Override
    public String syncBusinessPartner(IBusinessPartner businessPartner, B1Connection b1Connection,List<DataTemple> dataTempleList){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IBusinessPartners businessPartners = SBOCOMUtil.newBusinessPartners(company);
            businessPartners.setCardCode(businessPartner.getCardCode());
            businessPartners.setCardName(businessPartner.getCardName());

            // TODO userfileds


            int rstCode;
            if(businessPartners.getByKey(businessPartner.getCardCode())){
                rstCode = businessPartners.update();
            }else {
                rstCode = businessPartners.add();
            }
            if(rstCode == 0){
                return company.getNewObjectKey();
            }else {
                throw new B1Exception(company.getLastErrorCode() + ":" + company.getLastErrorDescription());
            }

        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }catch (Exception e){
            throw e;
        }
    }
}
