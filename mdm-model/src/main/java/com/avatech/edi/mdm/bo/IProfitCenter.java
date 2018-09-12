package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface IProfitCenter extends IMDMMasterData {

    String getPrcCode();

    void setPrcCode(String prcCode);

    String getPrcName();

    void setPrcName(String prcName);

    Integer getDimCode();

    void setDimCode(Integer dimCode);

    String getActive();

    void setActive(String active);

    String getCostCenterType();

    void setCostCenterType(String costCenterType);

    String getGroupCode();

    void setGroupCode(String groupCode);

    String getLocked();

    void setLocked(String locked);
}
