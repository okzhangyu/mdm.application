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

    String getRef2();

    void setRef2(String ref2);

    String getEmpId();

    void setEmpId(String empId);

    String getEmpName();

    void setEmpName(String empName);

    String getDeptId();

    void setDeptId(String deptId);

    Integer getbPLId();

    void setbPLId(Integer bPLId);

    List<ProjectReportItem> getProjectReportItems();

    void setProjectReportItems(List<ProjectReportItem> projectReportItems);
}
