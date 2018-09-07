package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public interface IBusinessPartnerGroup extends IMDMMasterData {

    String getGrpCode();

    void setGrpCode(String grpCode);

    String getGrpName();

    void setGrpName(String grpName);
}
