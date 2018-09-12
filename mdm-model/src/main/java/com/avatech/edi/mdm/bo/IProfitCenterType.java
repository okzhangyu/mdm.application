package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IProfitCenterType extends IMDMMasterData {

    Integer getDimCode();

    void setDimCode(Integer prjCode);

    String getDimName();

    void setDimName(String prjName);

    String getActive();

    void setActive(String active);

    String getDimDesc();

    void setDimDesc(String validFrom);

}
