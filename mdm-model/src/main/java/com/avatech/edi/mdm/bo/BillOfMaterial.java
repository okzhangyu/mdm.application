package com.avatech.edi.mdm.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AVA_PM_VIEW_OBOM")
public class BillOfMaterial implements IBillOfMaterial {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Companydb")
    private String companyDB;

    @Column(name = "Docentry")
    private Integer docEntry;

    @Column(name = "Bpcode")
    private String bpCode;

    @Column(name = "Itemcode")
    private String itemCode;

    @Column(name = "Itemname")
    private String itemName;

    @Column(name = "Version")
    private String version;

    @Column(name = "Versiondesc")
    private String versionDesc;

    @Column(name = "Actived")
    private String actived;

    @Column(name = "Treetype")
    private String treeType;

    @Column(name = "Unitqty")
    private Double unitQty;

    @Column(name = "Uom")
    private String uom;

    @Column(name = "Towh")
    private String toWH;

    @Column(name = "Project")
    private String project;

    @Column(name = "Workorderno")
    private String workOrderNo;

    @Column(name = "Routcode")
    private String routCode;

    @Column(name = "Outputwkc")
    private String outPutWkc;

    @Column(name = "Validdatef")
    private String validDateF;

    @Column(name = "Validdatet")
    private String validDateT;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "Optype")
    private String opType;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "Itemtype")
    private String itemType;

    @Column(name = "Projectname")
    private String projectName;

    @Column(name = "Docdate")
    private String docDtae;
    @Column(name="HTH")
    private String hth;

    @Column(name = "HTMC")
    private String htmc;

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOpType(){
        return opType;
    }

    public void setOpType(String opType){
        this.opType = opType;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Bomdocentry")
    private List<CompontOfMaterialListItem> compontOfMaterialListItems;

    public BillOfMaterial(){
        this.compontOfMaterialListItems = new ArrayList<>();
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Override
    public String getVersionDesc() {
        return versionDesc;
    }

    @Override
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
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
    public String getWorkOrderNo() {
        return workOrderNo;
    }

    @Override
    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
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
    public String getValidDateF() {
        return validDateF;
    }

    @Override
    public void setValidDateF(String validDateF) {
        this.validDateF = validDateF;
    }

    @Override
    public String getValidDateT() {
        return validDateT;
    }

    @Override
    public void setValidDateT(String validDateT) {
        this.validDateT = validDateT;
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
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public List<CompontOfMaterialListItem> getCompontOfMaterialListItems() {
        return compontOfMaterialListItems;
    }

    @Override
    public void setCompontOfMaterialListItems(List<CompontOfMaterialListItem> compontOfMaterialListItems) {
        this.compontOfMaterialListItems = compontOfMaterialListItems;
    }

    @Override
    public void setDocDate(String docDate) {
        this.docDtae=docDate;

    }

    @Override
    public String getDocDate() {
        return docDtae;
    }

    @Override
    public void setHTH(String hth) {

    }

    @Override
    public String getHTH() {
        return hth;
    }

    @Override
    public void setHTMC(String HTMC) {

    }

    @Override
    public String getHTMC() {
        return htmc;
    }

    @Override
    public String getItemType() {
        return itemType;
    }

    @Override
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "BillOfMaterial{" +
                "\"uniqueKey\":\"" + uniqueKey + '\'' +
                "\",\" companyDB\":\"" + companyDB + '\'' +
                "\",\" docEntry=" + docEntry +
                "\",\" bpCode\":\"" + bpCode + '\'' +
                "\",\" itemCode\":\"" + itemCode + '\'' +
                "\",\" itemName\":\"" + itemName + '\'' +
                "\",\" version\":\"" + version + '\'' +
                "\",\" versionDesc\":\"" + versionDesc + '\'' +
                "\",\" actived\":\"" + actived + '\'' +
                "\",\" treeType\":\"" + treeType + '\'' +
                "\",\" unitQty=" + unitQty +
                "\",\" uom\":\"" + uom + '\'' +
                "\",\" toWH\":\"" + toWH + '\'' +
                "\",\" project\":\"" + project + '\'' +
                "\",\" workOrderNo\":\"" + workOrderNo + '\'' +
                "\",\" routCode\":\"" + routCode + '\'' +
                "\",\" outPutWkc\":\"" + outPutWkc + '\'' +
                "\",\" validDateF\":\"" + validDateF + '\'' +
                "\",\" validDateT\":\"" + validDateT + '\'' +
                "\",\" remarks\":\"" + remarks + '\'' +
                "\",\" opType\":\"" + opType + '\'' +
                "\",\" creator\":\"" + creator + '\'' +
                "\",\" compontOfMaterialListItems\":" + compontOfMaterialListItems +
                '}';
    }
}
