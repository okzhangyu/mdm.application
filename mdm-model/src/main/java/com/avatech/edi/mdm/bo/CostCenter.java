package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public class CostCenter implements ICostCenter{


    private String prcCode;
    @Override
    public String getPrcCode() {
        return prcCode;
    }

    @Override
    public void setPrcCode(String prcCode) {
        this.prcCode = prcCode;
    }

    private String prcName;
    @Override
    public String getPrcName() {
        return prcName;
    }

    @Override
    public void setPrcName(String prcName) {
        this.prcName = prcName;
    }

    private Integer dimCode;
    @Override
    public Integer getDimCode() {
        return dimCode;
    }

    @Override
    public void setDimCode(Integer dimCode) {
        this.dimCode = dimCode;
    }

    private String active;
    @Override
    public String getActive() {
        return active;
    }

    @Override
    public void setActive(String active) {
        this.active = active;
    }
}
