package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IProfitCenter;
import com.avatech.edi.mdm.config.B1Connection;
import com.sap.smb.sbo.api.*;
import org.springframework.stereotype.Component;

@Component
public class B1ProfitCenterServiceImp implements B1ProfitCenterService {

    private static final String YES = "Y";
    @Override
    public String syncProfitCenter(IProfitCenter profitCenter, B1Connection b1Connection){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IProfitCentersService profitCentersService = SBOCOMUtil.newProfitCentersService(company.getCompanyService());
//            com.sap.smb.sbo.api.IProfitCenter iProfitCenter = (com.sap.smb.sbo.api.IProfitCenter)profitCentersService
//                    .getDataInterface(SBOCOMConstants.ProfitCentersServiceDataInterfaces_pcsProfitCenter);
            IProfitCenterParams profitCenterParams = (IProfitCenterParams) profitCentersService
                    .getDataInterface(SBOCOMConstants.ProfitCentersServiceDataInterfaces_pcsProfitCenterParams);

            profitCenterParams.setCenterCode(profitCenter.getPrcCode());
            com.sap.smb.sbo.api.IProfitCenter iProfitCenter = profitCentersService.getProfitCenter(profitCenterParams);

            iProfitCenter.setCenterCode(profitCenter.getPrcCode());
            iProfitCenter.setInWhichDimension(profitCenter.getDimCode());
            iProfitCenter.setActive(YES.equals(profitCenter.getActive())?SBOCOMConstants.BoYesNoEnum_tYES:SBOCOMConstants.BoYesNoEnum_tNO);
            iProfitCenter.setCenterName(profitCenter.getPrcName());
            iProfitCenter.setCostCenterType(profitCenter.getCostCenterType());
            iProfitCenter.setGroupCode(profitCenter.getGroupCode());

            // TODO userfileds

            if(iProfitCenter == null){
                profitCentersService.addProfitCenter(iProfitCenter);
            }else {
                profitCentersService.updateProfitCenter(iProfitCenter);
            }

            return profitCenter.getPrcCode();
        }catch (SBOCOMException e) {
            throw new B1Exception(e);
        }catch (Exception e){
            throw e;
        }
    }
}
