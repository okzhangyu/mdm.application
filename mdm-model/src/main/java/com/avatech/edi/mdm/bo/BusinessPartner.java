package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public class BusinessPartner implements IBusinessPartner {

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
