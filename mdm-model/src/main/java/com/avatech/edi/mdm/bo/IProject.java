package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IProject extends IMDMMasterData {

    String getPrjCode();

    void setPrjCode(String prjCode);

    String getPrjName();

    void setPrjName(String prjName);

    String getActive();

    void setActive(String active);

    String getValidFrom();

    void setValidFrom(String validFrom);

    String getValidTo();

    void setValidTo(String validTo);

}
