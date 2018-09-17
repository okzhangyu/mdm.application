package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IBusinessPartnerGroup;
import com.avatech.edi.mdm.businessone.config.B1Data;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class B1BusinessPartnerGroupServiceImp implements B1BusinessPartnerGroupService {

    @Override
    public String syncBPGroup(IBusinessPartnerGroup businessPartnerGroup, B1Connection b1Connection,List<DataTemple> dataTempleList){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IBusinessPartnerGroups bpGroup = SBOCOMUtil.newBusinessPartnerGroups(company);

            Boolean isExist = bpGroup.getByKey(businessPartnerGroup.getGrpCode());
            bpGroup.setName(businessPartnerGroup.getGrpName());
            bpGroup.setType(getTypeValue(businessPartnerGroup.getType()));
            //bpGroup.s
            //bpGroup.setType(businessPartnerGroup.getType());

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

    private Integer getTypeValue(String typeName){
        switch (typeName){
            case B1Data.CUSTOMER:return SBOCOMConstants.BoBusinessPartnerGroupTypes_bbpgt_CustomerGroup;
            case B1Data.SUPPLIER:return SBOCOMConstants.BoBusinessPartnerGroupTypes_bbpgt_VendorGroup;
            default:throw new B1Exception("类型为空或不匹配");
        }
    }
}
