package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IAccount;
import com.avatech.edi.mdm.businessone.masterdata.B1AccountService;
import com.avatech.edi.mdm.bo.IMDMMasterData;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Service
public class AccountService extends AbsMasterDataService{

    @Autowired
    private IBORepositoryAccount boRepositoryAccount;

    @Autowired
    private B1AccountService b1AccountService;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryAccount.findAccountByUniqueKey(key.toString());
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection,java.util.List<DataTemple> dataTemples) {
        SyncResult result = new SyncResult();
        result.setObjectKey(masterData.getUniqueKey());

        try{
            if(masterData instanceof IAccount){
                String key = b1AccountService.syncAccount((IAccount) masterData,b1Connection,dataTemples);
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
