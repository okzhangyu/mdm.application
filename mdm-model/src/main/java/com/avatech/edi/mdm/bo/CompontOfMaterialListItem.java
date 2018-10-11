package com.avatech.edi.mdm.bo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AVA_PM_VIEW_BOM1")
public class CompontOfMaterialListItem implements ICompontOfMaterialListItem {

    @Id
    @Column(name = "Uniquekey")
    private String  uniqueKey;

    @Column(name = "Companydb")
    private String companyDB;

    @Column(name = "Docentry")
    private Integer docEntry;

    @Column(name = "Lineid")
    private Integer lineId;

    @Column(name = "Linestatus")
    private String lineStatus;

    @Column(name = "Childnum")
    private Integer childNum;

    @Column(name = "Itemcode")
    private String itemCode;

    @Column(name = "Itemname")
    private String itemName;

    @Column(name = "Bomver")
    private String bomVer;

    @Column(name = "Quantity")
    private Double quantity;

    @Column(name = "Whscode")
    private String whsCode;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Uom")
    private String uom;

    @Column(name = "Udf1")
    private String udf1;

    @Column(name = "Udf2")
    private String udf2;

    @Column(name = "Udf3")
    private String udf3;

    @Column(name = "Udf4")
    private String udf4;

    @Column(name = "Udf5")
    private String udf5;

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getCompanyDB() {
        return companyDB;
    }

    public void setCompanyDB(String companyDB) {
        this.companyDB = companyDB;
    }

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
