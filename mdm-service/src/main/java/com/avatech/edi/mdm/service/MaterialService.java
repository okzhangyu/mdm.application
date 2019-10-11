package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.bo.IBusinessMaterial;
import com.avatech.edi.mdm.bo.IMDMMasterData;
import com.avatech.edi.mdm.bo.Material;
import com.avatech.edi.mdm.businessone.B1Exception;
import com.avatech.edi.mdm.businessone.masterdata.B1BusinessMaterialService;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.B1Data;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.IBORepositoryMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MaterialService extends AbsMasterDataService {
    @Autowired
    private IBORepositoryMaterial repositoryMaterial;

    @Autowired
    private B1BusinessMaterialService b1BusinessMaterialService;
    @Override
    public IMDMMasterData fetchMasterData(Object key) {
        return repositoryMaterial.findMaterialByUniqueKey(key.toString());
    }

    @Override
    public SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection, List<DataTemple> dataTemples) {
        SyncResult syncResult = new SyncResult();
        syncResult.setObjectKey(masterData.getUniqueKey());
        try {
            if (masterData instanceof IBusinessMaterial){
                String key = b1BusinessMaterialService.syncMaterial(b1Connection, (Material) masterData,dataTemples);
                syncResult.setCode(B1Data.SYNC_OK);
                syncResult.setObjectKey(masterData.getUniqueKey());
                syncResult.setMessage(B1Data.SYNC_OK_MSG);
                syncResult.setReturnKey(key);
            }

        }catch (B1Exception e){
            syncResult.setCode("-1");
            syncResult.setMessage(e.getMessage());
        }
        catch (Exception e){
            syncResult.setCode("-1");
            syncResult.setMessage(e.getMessage());
        }

        return syncResult;
    }
}
