package com.avatech.edi.mdm.bo;

public class Material extends MDMMasterData implements IBusinessMaterial {

    private String uniqueKey;

    private String ItemCode;

    private String ItemName;

    private String ItemGroup;

    private String batchNo;

    private String serialNo;

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemGroup() {
        return ItemGroup;
    }

    public void setItemGroup(String itemGroup) {
        ItemGroup = itemGroup;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String getUniqueKey() {
        return uniqueKey;
    }


    @Override
    public void setUniqueKey(String uniqueKey) {
       this.uniqueKey = uniqueKey;
    }
}
