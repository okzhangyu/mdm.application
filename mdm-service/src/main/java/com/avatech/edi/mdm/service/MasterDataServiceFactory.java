package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.config.BusinessType;
import com.avatech.edi.mdm.dto.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Component
public class MasterDataServiceFactory {

    @Autowired
    private AcountService acountService;

    private BaseMasterDataService service;

    public BaseMasterDataService getServiceInstance(MasterData masterData){
        if(masterData.getObjectCode() != null && !masterData.getObjectCode().isEmpty()){
            switch (masterData.getObjectCode()){
                case BusinessType.ACCOUNT:service = acountService;break;

            }
        }
        return service;
    }
}
