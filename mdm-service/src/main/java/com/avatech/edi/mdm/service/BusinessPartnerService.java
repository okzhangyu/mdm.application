package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IMDMMasterData;
import com.avatech.edi.mdm.bo.IBusinessPartner;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.masterdata.B1BusinessPartnerService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryBusinessPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Service
public class BusinessPartnerService extends AbsMasterDataService{


    @Autowired
    private IBORepositoryBusinessPartner boRepositoryBusinessPartner;

    @Autowired
    private B1BusinessPartnerService b1BusinessPartnerService;

    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return boRepositoryBusinessPartner.findBusinessPartnerByUniqueKey(key.toString());
    }


    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection,java.util.List<DataTemple> dataTemples) {
        SyncResult result = new SyncResult();
        result.setObjectKey(masterData.getUniqueKey());

        try{
            if(masterData instanceof IBusinessPartner){
                String key = b1BusinessPartnerService.syncBusinessPartner((IBusinessPartner) masterData,b1Connection,dataTemples);
                result.setCode(B1Data.SYNC_OK);
                result.setObjectKey(masterData.getUniqueKey());
                result.setMessage(B1Data.SYNC_OK_MSG);
                result.setReturnKey(key);
            }

        }catch (B1Exception e){
            result.setCode("-1");
            result.setMessage(e.getMessage());
        }
        catch (Exception e){
            result.setCode("-1");
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
