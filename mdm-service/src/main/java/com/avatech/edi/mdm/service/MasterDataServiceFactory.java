package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.config.BusinessType;
import com.avatech.edi.mdm.dto.MasterData;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public class MasterDataServiceFactory {

    private BaseMasterDataService service;

    public BaseMasterDataService getServiceInstance(MasterData masterData){
        if(masterData.getObjectCode() != null && !masterData.getObjectCode().isEmpty()){
            switch (masterData.getObjectCode()){
                case BusinessType.ACCOUNT:service = new AcountService();break;

            }
        }
        return service;
    }
}
