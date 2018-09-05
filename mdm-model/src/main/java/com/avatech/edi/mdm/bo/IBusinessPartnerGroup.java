package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public interface IBusinessPartnerGroup extends IBOMasterData {

    String getGrpCode();

    void setGrpCode(String grpCode);

    String getGrpName();

    void setGrpName(String grpName);
}
