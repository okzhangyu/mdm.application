package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.bo.IProfitCenter;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.*;
import com.sap.smb.sbo.wrapper.com.Dispatch;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class B1ProfitCenterServiceImp implements B1ProfitCenterService {

    private static final String YES = "Y";
    @Override
    public String syncProfitCenter(IProfitCenter profitCenter, B1Connection b1Connection,List<DataTemple> dataTempleList){
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        com.sap.smb.sbo.api.IProfitCenter iProfitCenter = null;
        ProfitCenterParams profitCenterParams = null;
        ProfitCentersParams pcparams = null;
        ProfitCentersService profitCentersService = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            profitCentersService = (ProfitCentersService)SBOCOMUtil.newProfitCentersService(company.getCompanyService());
            pcparams = (ProfitCentersParams) profitCentersService.getProfitCenterList();
            profitCenterParams = (ProfitCenterParams) pcparams.add();
            profitCenterParams.setCenterCode(profitCenter.getPrcCode());

            Boolean isExist = true;
            try
            {
                iProfitCenter = profitCentersService.getProfitCenter(profitCenterParams);
            }catch (Exception e){
                isExist = false;
            }
            if(iProfitCenter == null){
                Object obj = profitCentersService.getDataInterface(SBOCOMConstants.ProfitCentersServiceDataInterfaces_pcsProfitCenter);
                iProfitCenter = new com.sap.smb.sbo.api.ProfitCenter(obj);
            }
            iProfitCenter.setCenterCode(profitCenter.getPrcCode());
            iProfitCenter.setInWhichDimension(profitCenter.getDimCode());
            iProfitCenter.setActive(YES.equals(profitCenter.getActive())?SBOCOMConstants.BoYesNoEnum_tYES:SBOCOMConstants.BoYesNoEnum_tNO);
            iProfitCenter.setCenterName(profitCenter.getPrcName());
            iProfitCenter.setCostCenterType(profitCenter.getCostCenterType());
            iProfitCenter.setGroupCode(profitCenter.getGroupCode());
            if(profitCenter.getValidFrom()!= null)
                iProfitCenter.setEffectivefrom(profitCenter.getValidFrom());
            if(profitCenter.getValidTo() != null)
                iProfitCenter.setEffectiveTo(profitCenter.getValidTo());

            // TODO userfileds

            if(!isExist){
                profitCentersService.addProfitCenter(iProfitCenter);
            }else {
                profitCentersService.updateProfitCenter(iProfitCenter);
            }
            return profitCenter.getPrcCode();
        }catch (Exception e){
            throw new B1Exception(e.getMessage());
        }finally {
            if(iProfitCenter != null){
                iProfitCenter.release();
            }
            if(profitCenterParams != null){
                profitCenterParams.release();
            }
            if(pcparams != null){
                pcparams.release();
            }
            if(profitCentersService!=null){
                profitCentersService.release();
            }
        }
    }
}
