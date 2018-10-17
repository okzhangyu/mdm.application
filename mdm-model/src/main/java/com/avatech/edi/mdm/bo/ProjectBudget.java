package com.avatech.edi.mdm.bo;

import java.util.ArrayList;
import java.util.List;

public class ProjectBudget implements IProjectBudget{

    public ProjectBudget(){
        this.projectBudgetItemList = new ArrayList<>();
    }

    private String companyDB;

    private Integer docEntry;

    private String prjCode;

    private String prjName;

    private String deptName;

    private String prjTypeCode;

    private String prjTypeName;

    private String cardCode;

    private String cardName;

    private String managerId;

    private String managerName;

    private String deptId;

    private Integer bplId;

    private String remarks;

    private String udf1;

    private String udf2;

    private String udf3;

    private String udf4;

    private String udf5;

    private List<ProjectBudgetItem> projectBudgetItemList;

    public String getCompanyDB() {
        return companyDB;
    }

    public void setCompanyDB(String companyDB) {
        this.companyDB = companyDB;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public String getPrjCode() {
        return prjCode;
    }

    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjTypeCode() {
        return prjTypeCode;
    }

    public void setPrjTypeCode(String prjTypeCode) {
        this.prjTypeCode = prjTypeCode;
    }

    public String getPrjTypeName() {
        return prjTypeName;
    }

    public void setPrjTypeName(String prjTypeName) {
        this.prjTypeName = prjTypeName;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    @Override
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getBplId() {
        return bplId;
    }

    public void setBplId(Integer bplId) {
        this.bplId = bplId;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public String getRemarks(){
        return remarks;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public List<ProjectBudgetItem> getProjectBudgetItemList() {
        return projectBudgetItemList;
    }

    public void setProjectBudgetItemList(List<ProjectBudgetItem> projectBudgetItemList) {
        this.projectBudgetItemList = projectBudgetItemList;
    }
}
