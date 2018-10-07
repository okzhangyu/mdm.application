package com.avatech.edi.mdm.bo;

public class CompontOfMaterialListItem implements ICompontOfMaterialListItem {

    private Integer docEntry;

    private Integer lineId;
    
    private String lineStatus;
    
    private Integer childNum;

    private String itemCode;
    
    private String itemName;

    private String bomVer;

    private Double quantity;

    private String whsCode;

    private Double price;

    private String uom;

    private String udf1;
    
    private String udf2;
    
    private String udf3;
    
    private String udf4;
    
    private String udf5;

    @Override
    public Integer getDocEntry() {
        return docEntry;
    }

    @Override
    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    @Override
    public Integer getLineId() {
        return lineId;
    }

    @Override
    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    @Override
    public String getLineStatus() {
        return lineStatus;
    }

    @Override
    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    @Override
    public Integer getChildNum() {
        return childNum;
    }

    @Override
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    @Override
    public String getItemCode() {
        return itemCode;
    }

    @Override
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBOMVer() {
        return bomVer;
    }

    public void setBOMVer(String bomVer) {
        this.bomVer = bomVer;
    }

    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getWhsCode() {
        return whsCode;
    }

    @Override
    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUOM() {
        return uom;
    }

    public void setUOM(String uom) {
        this.uom = uom;
    }

    public String getUDF1() {
        return udf1;
    }

    public void setUDF1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUDF2() {
        return udf2;
    }

    public void setUDF2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUDF3() {
        return udf3;
    }

    public void setUDF3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUDF4() {
        return udf4;
    }

    public void setUDF4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUDF5() {
        return udf5;
    }

    public void setUDF5(String udf5) {
        this.udf5 = udf5;
    }
}
