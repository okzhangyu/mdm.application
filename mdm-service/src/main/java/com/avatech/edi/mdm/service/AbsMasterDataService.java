package com.avatech.edi.mdm.service;

import com.avatech.edi.mdm.IMDMMasterData;
import com.avatech.edi.mdm.config.B1Connection;
import com.avatech.edi.mdm.config.DataTemple;
import com.avatech.edi.mdm.config.DataTempleKey;
import com.avatech.edi.mdm.data.ArrayList;
import com.avatech.edi.mdm.data.List;
import com.avatech.edi.mdm.dto.MasterData;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.repository.config.IRepositoryDataTemple;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public abstract class AbsMasterDataService implements BaseMasterDataService{

    private final static String NOT_EXISTS_DATA = "未找到主数据";

    public abstract IMDMMasterData fetchMasterData(Object key);

    /**
     * 同步主数据
     * @param masterData 同步信息
     * @param b1Connection B1连接信息
     * @param dataTemples 同步模版
     * @return
     */
    public abstract SyncResult saveMasterData(IMDMMasterData masterData, B1Connection b1Connection,java.util.List<DataTemple> dataTemples);

    @Autowired
    private IRepositoryDataTemple repositoryDataTemple;

    @Override
    public List<SyncResult> syncMasterData(MasterData mdmMasterData) throws Exception{
        List<SyncResult> syncResults = new ArrayList<>();
        // TODO 获取B1配置信息
        B1Connection b1Connection = new B1Connection();

        java.util.List<DataTemple> dataTemples = repositoryDataTemple.findByDataTempleKey_Code(mdmMasterData.getObjectCode());
        if(dataTemples == null || dataTemples.size() == 0){
            throw new Exception("未找到匹配的同步模板");
        }
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
                   syncResult = saveMasterData(data,b1Connection,dataTemples);
                   syncResults.add(syncResult);
               }catch (Exception e){
                   syncResults.add(SyncResult.error(data.getUniqueKey(), e.getMessage()));
               }
           }
       }

        return syncResults;
    }

}
