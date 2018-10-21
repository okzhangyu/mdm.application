package com.avatech.edi.mdm.bo;

import java.util.List;

public interface IProjectBudget {

    String getCompanyDB();

    void setCompanyDB(String companyDB);

    Integer getDocEntry();

    void setDocEntry(Integer docEntry);

    String getPrjCode();

    void setPrjCode(String prjCode);

    String getPrjName();

    void setPrjName(String prjName);

    String getPrjTypeCode();

    void setPrjTypeCode(String prjTypeCode);

    String getPrjTypeName();

    void setPrjTypeName(String prjTypeName);

    String getCardCode();

    void setCardCode(String cardCode);

    String getCardName();

    void setCardName(String cardName);

    String getManagerId();

    void setManagerId(String managerId);

    String getManagerName();

    void setManagerName(String managerName);

    String getDeptId();

    void setDeptId(String deptId);

    String getDeptName();

    void setDeptName(String deptName);

    Integer getBplId();

    void setBplId(Integer bplId);

    String getRemarks();

    void setRemarks(String remarks);

    String getUdf1();

    void setUdf1(String udf1);

    String getUdf2();

    void setUdf2(String udf2);

    String getUdf3();

    void setUdf3(String udf3);

    String getUdf4();

    void setUdf4(String udf4);

    String getUdf5();

    void setUdf5(String udf5);

    List<ProjectBudgetItem> getProjectBudgetItemList();

    void setProjectBudgetItemList(List<ProjectBudgetItem> projectBudgetItemList);

}
