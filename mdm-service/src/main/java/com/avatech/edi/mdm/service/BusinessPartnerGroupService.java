package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IMDMMasterData;
import com.avatech.edi.mdm.bo.IBusinessPartnerGroup;
import com.avatech.edi.mdm.businessone.masterdata.B1BusinessPartnerGroupService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryBusinessPartnerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Service
public class BusinessPartnerGroupService extends AbsMasterDataService{

    @Autowired
    private IBORepositoryBusinessPartnerGroup boRepositoryBusinessPartnerGroup;

    @Autowired
    private B1BusinessPartnerGroupService b1BusinessPartnerGroupService;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryBusinessPartnerGroup.findBusinessPartnerGroupByUniqueKey(new Integer(key.toString()));
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection,java.util.List<DataTemple> dataTemples) {
        SyncResult result = new SyncResult();
        result.setObjectKey(masterData.getUniqueKey());

        try{
            if(masterData instanceof IBusinessPartnerGroup){
                String key = b1BusinessPartnerGroupService.syncBPGroup((IBusinessPartnerGroup) masterData,b1Connection,dataTemples);
                result.setCode(B1Data.SYNC_OK);
                result.setMessage(B1Data.SYNC_OK_MSG);
                result.setReturnKey(key);
            }

        }catch (Exception e){
            result.setCode("-1");
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
