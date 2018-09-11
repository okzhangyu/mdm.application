package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.IMDMMasterData;
import com.avatech.edi.mdm.bo.IBOMasterData;
import com.avatech.edi.mdm.bo.IProfitCenter;
import com.avatech.edi.mdm.businessone.masterdata.B1ProfitCenterService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryAccount;
import com.avatech.edi.mdm.repository.IBORepositoryProfitCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Service
public class ProfitCenterService extends AbsMasterDataService{

    @Autowired
    private IBORepositoryProfitCenter boRepositoryProfitCenter;

    @Autowired
    private B1ProfitCenterService b1ProfitCenterService;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryProfitCenter.findByPrcCode(key.toString());
    }


    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection,java.util.List<DataTemple> dataTemples) {
        SyncResult result = new SyncResult();
        result.setObjectKey(masterData.getUniqueKey());

        try{
            if(masterData instanceof IProfitCenter){
                String key = b1ProfitCenterService.syncProfitCenter((IProfitCenter) masterData,b1Connection,dataTemples);
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
