package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public interface IBusinessPartnerGroup extends IMDMMasterData {

    Integer getGrpCode();

    void setGrpCode(Integer grpCode);

    String getGrpName();

    void setGrpName(String grpName);

    Integer getType();

    void setType(Integer type);
}
