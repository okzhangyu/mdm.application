package com.avatech.edi.mdm.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@Entity
@Table(name ="AVA_MDM_VIEW_OPRC")
public class ProfitCenter extends MDMMasterData implements IProfitCenter {


    @Id
    @Column(name = "Prccode")
    private String prcCode;
    @Override
    public String getPrcCode() {
        return prcCode;
    }

    @Override
    public void setPrcCode(String prcCode) {
        this.prcCode = prcCode;
    }

    @Column(name = "Prcname")
    private String prcName;
    @Override
    public String getPrcName() {
        return prcName;
    }

    @Override
    public void setPrcName(String prcName) {
        this.prcName = prcName;
    }

    @Column(name = "Dimcode")
    private Integer dimCode;
    @Override
    public Integer getDimCode() {
        return dimCode;
    }

    @Override
    public void setDimCode(Integer dimCode) {
        this.dimCode = dimCode;
    }

    @Column(name = "Active")
    private String active;
    @Override
    public String getActive() {
        return active;
    }

    @Override
    public void setActive(String active) {
        this.active = active;
    }

    @Column(name = "Cctypecode")
    private String costCenterType;
    @Override
    public String getCostCenterType() {
        return costCenterType;
    }

    @Override
    public void setCostCenterType(String costCenterType) {
        this.costCenterType = costCenterType;
    }

    @Column(name = "Grpcode")
    private String groupCode;
    @Override
    public String getGroupCode() {
        return groupCode;
    }

    @Override
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "Locked")
    private String locked;

    @Override
    public String getLocked() {
        return locked;
    }

    @Override
    public void setLocked(String locked) {
        this.locked = locked;
    }
}
