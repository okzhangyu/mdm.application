package com.avatech.edi.mdm.bo;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

public class ProjectReport implements IProjectReport{

    public  ProjectReport(){
        this.projectReportItems = new ArrayList<>();
    }
    private String companyDB;

    private Integer docEntry;

    private String remarks;

    private String ref1;

    private String ref2;


    private String empId;

    private String empName;

    private String deptId;

    private Integer bPLId;


    private List<ProjectReportItem> projectReportItems;

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

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getbPLId() {
        return bPLId;
    }

    public void setbPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    public List<ProjectReportItem> getProjectReportItems() {
        return projectReportItems;
    }

    public void setProjectReportItems(List<ProjectReportItem> projectReportItems) {
        this.projectReportItems = projectReportItems;
    }
}
