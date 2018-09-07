package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.IMDMMasterData;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.hana.BORepositoryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Service
public class AcountService extends AbsMasterDataService{

    @Autowired
    private BORepositoryAccount boRepositoryAccount;



    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryAccount.fetchAccount(key.toString());
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection) {
        return null;
    }

   }
