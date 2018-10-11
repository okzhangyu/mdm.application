package com.avatech.edi.mdm.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillOfMaterial implements IBillOfMaterial {

    private String companyDB;

    private Integer docEntry;

    private String bpCode;

    private String itemCode;

    private String itemName;

    private String version;

    private String actived;

    private String treeType;

    private Double unitQty;

    private String uom;

    private String toWH;

    private String project;

    private String routCode;

    private String outPutWkc;

    private Date validDateF;

    private Date validDateT;

    private Integer bPLId;

    private String remarks;

    private List<CompontOfMaterialListItem> compontOfMaterialListItems;

    public BillOfMaterial(){
        this.compontOfMaterialListItems = new ArrayList<>();
    }

    @Override
    public String getCompanyDB() {
        return companyDB;
    }

    @Override
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
    public String getBPCode() {
        return bpCode;
    }

    @Override
    public void setBPCode(String bPCode) {
        this.bpCode = bPCode;
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
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getActived() {
        return actived;
    }

    @Override
    public void setActived(String actived) {
        this.actived = actived;
    }

    @Override
    public String getTreeType() {
        return treeType;
    }

    @Override
    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    @Override
    public Double getUnitQty() {
        return unitQty;
    }

    @Override
    public void setUnitQty(Double unitQty) {
        this.unitQty = unitQty;
    }

    @Override
    public String getUom() {
        return uom;
    }

    @Override
    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public String getToWH() {
        return toWH;
    }

    @Override
    public void setToWH(String toWH) {
        this.toWH = toWH;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String getRoutCode() {
        return routCode;
    }

    @Override
    public void setRoutCode(String routCode) {
        this.routCode = routCode;
    }

    @Override
    public String getOutPutWkc() {
        return outPutWkc;
    }

    @Override
    public void setOutPutWkc(String outPutWkc) {
        this.outPutWkc = outPutWkc;
    }

    @Override
    public Date getValidDateF() {
        return validDateF;
    }

    @Override
    public void setValidDateF(Date validDateF) {
        this.validDateF = validDateF;
    }

    @Override
    public Date getValidDateT() {
        return validDateT;
    }

    @Override
    public void setValidDateT(Date validDateT) {
        this.validDateT = validDateT;
    }

    @Override
    public Integer getBPLId() {
        return bPLId;
    }

    @Override
    public void setBPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public List<CompontOfMaterialListItem> getCompontOfMaterialListItems() {
        return compontOfMaterialListItems;
    }

    @Override
    public void setCompontOfMaterialListItems(List<CompontOfMaterialListItem> compontOfMaterialListItems) {
        this.compontOfMaterialListItems = compontOfMaterialListItems;
    }
}
