package com.avatech.edi.businessone.masterdata;

import com.avatech.edi.businessone.B1Exception;
import com.avatech.edi.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IBusinessPartnerGroup;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.IBusinessPartnerGroups;
import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;

public class BusinessPartnerGroupService {

    public String syncBPGroup(IBusinessPartnerGroup businessPartnerGroup, B1Connection b1Connection){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IBusinessPartnerGroups bpGroup = SBOCOMUtil.newBusinessPartnerGroups(company);

            boolean isExist = bpGroup.getByKey(businessPartnerGroup.getGrpCode());
            bpGroup.setName(businessPartnerGroup.getGrpName());
            bpGroup.setType(businessPartnerGroup.getType());
            // TODO add userfileds

            int rstCode;
            if(isExist){
                rstCode = bpGroup.update();
            }else {
                rstCode = bpGroup.add();
            }

            if(rstCode == 0){
                return company.getNewObjectKey();
            }else {
                throw new B1Exception(company.getLastErrorCode() + ":"+company.getLastErrorDescription());
            }

        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }
        catch (Exception e){
            throw e;
        }
    }
}
