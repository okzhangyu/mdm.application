package com.avatech.edi.mdm.bo;

import com.sun.tools.javac.api.ClientCodeWrapper;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public class ProfitCenterType extends MDMMasterData implements IProfitCenterType {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Dimcode")
    private Integer dimCode;

    @Column(name = "DimName")
    private String dimName;

    @Column(name = "Active")
    private String active;

    @Column(name = "Dimdesc")
    private String dimDesc;

    @Override
    public String getUniqueKey() {
        return uniqueKey;
    }

    @Override
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Override
    public Integer getDimCode() {
        return dimCode;
    }

    @Override
    public void setDimCode(Integer dimCode) {
        this.dimCode = dimCode;
    }

    @Override
    public String getDimName() {
        return dimName;
    }

    @Override
    public void setDimName(String dimName) {
        this.dimName = dimName;
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
    public String getDimDesc() {
        return dimDesc;
    }

    @Override
    public void setDimDesc(String dimDesc) {
        this.dimDesc = dimDesc;
    }

}
