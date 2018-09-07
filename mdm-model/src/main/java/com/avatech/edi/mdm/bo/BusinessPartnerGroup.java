package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public class BusinessPartnerGroup extends MDMMasterData implements IBusinessPartnerGroup {

    private String grpCode;

    private String grpName;

    @Override
    public String getGrpCode() {
        return grpCode;
    }

    @Override
    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode;
    }

    @Override
    public String getGrpName() {
        return grpName;
    }

    @Override
    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }
}
