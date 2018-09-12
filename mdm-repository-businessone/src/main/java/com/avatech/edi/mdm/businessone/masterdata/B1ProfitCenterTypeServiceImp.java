package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.bo.IProfitCenterType;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.BORepositoryBusinessOne;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.sap.smb.sbo.api.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class B1ProfitCenterTypeServiceImp implements B1ProfitCenterTypeService {

    @Override
    public String syncProfitCenterType(IProfitCenterType profitCenterType, B1Connection b1Connection, List<DataTemple> dataTempleList) {
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            IDimensionsService dimensionsService = SBOCOMUtil.newDimensionsService(company.getCompanyService());
            IDimensionParams dimensionParams = (IDimensionParams)dimensionsService
                    .getDataInterface(SBOCOMConstants.DimensionsServiceDataInterfaces_dsDimensionParams);

            dimensionParams.setDimensionCode(profitCenterType.getDimCode());

            IDimension dimension = dimensionsService.getDimension(dimensionParams);
            if(dimension == null){
                dimension.setDimensionDescription(profitCenterType.getDimDesc());
                dimension.setIsActive(profitCenterType.getActive() == "Y"?SBOCOMConstants.BoYesNoEnum_tYES:SBOCOMConstants.BoYesNoEnum_tNO);
                dimensionsService.updateDimension(dimension);
            }
            return profitCenterType.getDimCode().toString();
        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }catch (Exception e){
            throw e;
        }
    }
}
