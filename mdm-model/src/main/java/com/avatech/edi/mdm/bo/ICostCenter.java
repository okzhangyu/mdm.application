package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface ICostCenter extends IBOMasterData {

    String getPrcCode();

    void setPrcCode(String prcCode);

    String getPrcName();

    void setPrcName(String prcName);

    Integer getDimCode();

    void setDimCode(Integer dimCode);

    String getActive();

    void setActive(String active);


}
