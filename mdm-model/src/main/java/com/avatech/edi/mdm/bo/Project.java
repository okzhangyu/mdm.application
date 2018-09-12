package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public class Project extends MDMMasterData implements IProject {

    private String prjCode;
    private String prjName;
    private String active;
    private String validFrom;
    private String validTo;

    @Override
    public String getPrjCode() {
        return prjCode;
    }

    @Override
    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    @Override
    public String getPrjName() {
        return prjName;
    }

    @Override
    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    @Override
    public String getActive() {
        return active;
    }

    @Override
    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String getValidFrom() {
        return validFrom;
    }

    @Override
    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public String getValidTo() {
        return validTo;
    }

    @Override
    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
