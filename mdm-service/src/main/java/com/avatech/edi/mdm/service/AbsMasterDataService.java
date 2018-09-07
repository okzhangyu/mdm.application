package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.IMDMMasterData;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.data.ArrayList;
import com.avatech.edi.mdm.data.List;
import com.avatech.edi.mdm.dto.MasterData;
import com.avatech.edi.mdm.dto.SyncResult;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public abstract class AbsMasterDataService implements BaseMasterDataService{

    private final static String NOT_EXISTS_DATA = "未找到主数据";

    public abstract IMDMMasterData fetchMasterData(Object key);

    public abstract SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection);

    @Override
    public List<SyncResult> syncMasterData(MasterData mdmMasterData){
        List<SyncResult> syncResults = new ArrayList<>();
        // TODO 获取B1配置信息
        B1Connection b1Connection = new B1Connection();

       if(mdmMasterData.getData() != null) {
           List<IMDMMasterData> masterDatas = new ArrayList<>();
           IMDMMasterData masterData;
           //获取主数据信息
           for (String key : mdmMasterData.getData()) {
               try {
                   masterData = this.fetchMasterData(key);
                   if (masterData == null) {
                       syncResults.add(SyncResult.error(key, NOT_EXISTS_DATA));
                       continue;
                   }
                   masterDatas.add(masterData);
               } catch (Exception e) {
                   syncResults.add(SyncResult.error(key, e.getMessage()));
               }
           }
           SyncResult syncResult;
           for (IMDMMasterData data:masterDatas) {
               try
               {
                   syncResult = saveMasterData(data,b1Connection);
                   syncResults.add(syncResult);
               }catch (Exception e){
                   syncResults.add(SyncResult.error(data.getUniqueKey(), e.getMessage()));
               }
           }
       }

        return syncResults;
    }

}
