package com.avatech.edi.mdm.bo;


import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AVA_PM_VIEW_BOM1")
public class CompontOfMaterialListItem implements ICompontOfMaterialListItem {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Bomdocentry")
    private String bomDocEntry;

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

    @Column(name = "Islocked")
    private String isLocked;

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

    @Column(name = "Docdate")
    private Date docDate;

    @Column(name = "Modelname")
    private String modelName;

    @Column(name = "Desdocentry")
    private Integer desDocEntry;

    @Column(name = "Deslinenum")
    private Integer desLineNum;

    @Column(name = "Remark")
    private String remark;

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
    public String getBOMDocEntry() {
        return bomDocEntry;
    }

    @Override
    public void setBOMDocEntry(String bomDocEntry) {
        this.bomDocEntry = bomDocEntry;
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


    @Override
    public String getIsLocked() {
        return isLocked;
    }

    @Override
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String getBOMVer() {
        return bomVer;
    }

    @Override
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

    @Override
    public String getUOM() {
        return uom;
    }

    @Override
    public void setUOM(String uom) {
        this.uom = uom;
    }

    @Override
    public String getUDF1() {
        return udf1;
    }

    @Override
    public void setUDF1(String udf1) {
        this.udf1 = udf1;
    }

    @Override
    public String getUDF2() {
        return udf2;
    }

    @Override
    public void setUDF2(String udf2) {
        this.udf2 = udf2;
    }

    @Override
    public String getUDF3() {
        return udf3;
    }

    @Override
    public void setUDF3(String udf3) {
        this.udf3 = udf3;
    }

    @Override
    public String getUDF4() {
        return udf4;
    }

    @Override
    public void setUDF4(String udf4) {
        this.udf4 = udf4;
    }

    @Override
    public String getUDF5() {
        return udf5;
    }

    @Override
    public void setUDF5(String udf5) {
        this.udf5 = udf5;
    }

    @Override
    public void setDocDate(Date docDate) {
        this.docDate=docDate;

    }

    @Override
    public Integer getDesDocEntry() {
        return desDocEntry;
    }

    @Override
    public void setDesDocEntry(Integer desDocEntry) {
        this.desDocEntry = desDocEntry;
    }

    @Override
    public Integer getDesLineNum() {
        return desLineNum;
    }

    @Override
    public void setDesLineNum(Integer desLineNum) {
        this.desLineNum = desLineNum;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public Date getDocDate() {
        return docDate;
    }


    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CompontOfMaterialListItem{" +
                "\"uniqueKey\":\"" + uniqueKey + '\'' +
                "\",\"bomDocEntry\":\"" + bomDocEntry + '\'' +
                "\",\"companyDB\":\"" + companyDB + '\'' +
                "\",\"docEntry\":\"" + docEntry +
                "\",\"lineId\":\"" + lineId +
                "\",\"lineStatus\":\"" + lineStatus + '\'' +
                "\",\"childNum\":\"" + childNum +
                "\",\"itemCode\":\"" + itemCode + '\'' +
                "\",\"itemName\":\"" + itemName + '\'' +
                "\",\"bomVer\":\"" + bomVer + '\'' +
                "\",\"quantity\":\"" + quantity +
                "\",\"whsCode\":\"" + whsCode + '\'' +
                "\",\"price\":\"" + price +
                "\",\"isLocked\":\"" + isLocked + '\'' +
                "\",\"uom\":\"" + uom + '\'' +
                "\",\"udf1\":\"" + udf1 + '\'' +
                "\",\"udf2\":\"" + udf2 + '\'' +
                "\",\"udf3\":\"" + udf3 + '\'' +
                "\",\"udf4\":\"" + udf4 + '\'' +
                "\",\"udf5\":\"" + udf5 + '\'' +
                "\",\"docDate\":\"" + docDate + '\'' +
                '}';
    }
}
