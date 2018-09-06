package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.IMDMMasterData;
import com.avatech.edi.mdm.bo.IBOMasterData;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryBusinessPartner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public class BusinessPartnerService extends AbsMasterDataService{


    @Autowired
    private IBORepositoryBusinessPartner boRepositoryBusinessPartner;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryBusinessPartner.fetchBusinessPartner(key.toString());
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection) {
        return null;
    }

}
