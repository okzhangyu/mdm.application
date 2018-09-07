package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public class BusinessPartner extends MDMMasterData implements IBusinessPartner {

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
