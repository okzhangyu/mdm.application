package com.avatech.edi.mdm.bo;

import java.util.List;

public interface IProjectReport {

    String getCompanyDB();

    void setCompanyDB(String companyDB);

    Integer getDocEntry();

    void setDocEntry(Integer docEntry);

    String getRemarks();

    void setRemarks(String remarks);

    String getRef1();

    void setRef1(String ref1);

    String getCreator();

    void setCreator(String creator);

    String getRef2();

    void setRef2(String ref2);

    String getEmpId();

    void setEmpId(String empId);

    String getEmpName();

    void setEmpName(String empName);

    String getDeptId();

    void setDeptId(String deptId);

    String getDeptName();

    void setDeptName(String deptName);

    Integer getbPLId();

    void setbPLId(Integer bPLId);

    String getWorkOrderNo();

    void setWorkOrderNo(String workOrderNo);

    String getProject();

    void setProject(String project);

    String getContractNo();

    void setContractNo(String contractNo);

    String getContractName();

    void setContractName(String contractName);

    List<ProjectReportItem> getProjectReportItems();

    void setProjectReportItems(List<ProjectReportItem> projectReportItems);
}
