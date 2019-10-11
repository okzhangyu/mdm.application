package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.ProfitCenterType;
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
    private MaterialService materialService;

    @Autowired
    private AccountService acountService;

    @Autowired
    private BusinessPartnerService businessPartnerService;

    @Autowired
    private BusinessPartnerGroupService businessPartnerGroupService;

    @Autowired
    private ProfitCenterService profitCenterService;

    @Autowired
    private ProfitCenterTypeService profitCenterTypeService;

    private BaseMasterDataService service;

    public BaseMasterDataService getServiceInstance(MasterData masterData){
        if(masterData.getObjectCode() != null && !masterData.getObjectCode().isEmpty()){
            switch (masterData.getObjectCode()){
                case BusinessType.ACCOUNT:service = acountService;break;
                case BusinessType.BUSINESSPARTNER :service = businessPartnerService;break;
                case BusinessType.BUSINESSPARTNERGROUP :service = businessPartnerGroupService;break;
                case BusinessType.PROFITCENTER :service = profitCenterService;break;
                case BusinessType.PROFITCENTERTYPE:service = profitCenterTypeService;break;
                case BusinessType.MATERIAL:service =materialService;break;

            }
        }
        return service;
    }
}
