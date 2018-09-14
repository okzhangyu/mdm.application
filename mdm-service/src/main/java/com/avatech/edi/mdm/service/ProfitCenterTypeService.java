package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IAccount;
import com.avatech.edi.mdm.bo.IMDMMasterData;
import com.avatech.edi.mdm.bo.IProfitCenterType;
import com.avatech.edi.mdm.businessone.masterdata.B1ProfitCenterTypeService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryProfitCenterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfitCenterTypeService extends AbsMasterDataService {

    @Autowired
    private IBORepositoryProfitCenterType boRepositoryProfitCenterType;

    @Autowired
    private B1ProfitCenterTypeService b1ProfitCenterTypeService;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryProfitCenterType.getProfitCenterTypeByUniqueKey(key.toString());
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection, List<DataTemple> dataTemples) {
        SyncResult result = new SyncResult();
        result.setObjectKey(masterData.getUniqueKey());

        try{
            if(masterData instanceof IProfitCenterType){
                String key = b1ProfitCenterTypeService.syncProfitCenterType((IProfitCenterType) masterData,b1Connection,dataTemples);
                result.setCode(B1Data.SYNC_OK);
                result.setObjectKey(masterData.getUniqueKey());
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
