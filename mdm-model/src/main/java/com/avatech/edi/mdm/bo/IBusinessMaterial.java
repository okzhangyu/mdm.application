package com.avatech.edi.mdm.bo;

public interface IBusinessMaterial extends IMDMMasterData {

    String getUniqueKey();

    void setUniqueKey(String uniqueKey);

    String getItemCode();

    void setItemCode(String itemCode);

    String getItemName();

    void setItemName(String itemName);

    String getBatchNo();

    void setBatchNo(String batchNo);

    String getSerialNo();

    void setSerialNo(String serialNo);

}
