package com.avatech.edi.mdm.service;

import com.avatech.edi.common.data.List;
import com.avatech.edi.mdm.dto.MasterData;
import com.avatech.edi.mdm.dto.SyncResult;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public interface BaseMasterDataService {

    List<SyncResult> syncMasterData(MasterData mdmMasterData) throws Exception;
}
