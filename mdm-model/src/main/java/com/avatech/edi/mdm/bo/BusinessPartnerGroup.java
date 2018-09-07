package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/5
 */
public class BusinessPartnerGroup extends MDMMasterData implements IBusinessPartnerGroup {

    private Integer grpCode;

    private String grpName;

    private Integer type;

    @Override
    public Integer getGrpCode() {
        return grpCode;
    }

    @Override
    public void setGrpCode(Integer grpCode) {
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

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }
}
