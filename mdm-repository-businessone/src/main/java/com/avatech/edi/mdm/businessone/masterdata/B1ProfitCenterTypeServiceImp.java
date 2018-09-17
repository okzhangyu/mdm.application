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
        IDimensionsService dimensionsService = null;
        DimensionsParams params = null;
        DimensionParams param = null;
        IDimension dimension = null;

        try {
            //get company info
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(b1Connection);
            company = boRepositoryBusinessOne.getCompany();

            dimensionsService = SBOCOMUtil.newDimensionsService(company.getCompanyService());
            params = (DimensionsParams)dimensionsService.getDimensionList();
            param = (DimensionParams)params.add();

            boolean isExsit = true;
            param.setDimensionCode(profitCenterType.getDimCode());
            try{
                dimension = dimensionsService.getDimension(param);
                isExsit = true;
            }catch (Exception e){
                isExsit = false;
            }
            dimension.setDimensionDescription(profitCenterType.getDimDesc());
            dimension.setIsActive(profitCenterType.getActive() == "Y"?SBOCOMConstants.BoYesNoEnum_tYES:SBOCOMConstants.BoYesNoEnum_tNO);

            if(!isExsit){
                dimensionsService.updateDimension(dimension);
            }else {
                throw new B1Exception("成本中心维度只能更新，不能添加，请手动添加");
            }
            return profitCenterType.getDimCode().toString();
        }catch (SBOCOMException e){
            throw new B1Exception(e);
        }catch (Exception e){
            throw e;
        }
    }
}
