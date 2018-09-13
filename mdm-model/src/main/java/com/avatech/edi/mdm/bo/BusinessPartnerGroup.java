package com.avatech.edi.mdm.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Fancy
 * @date 2018/9/5
 */
@Entity
@Table(name = "AVA_MDM_VIEW_OCRG")
public class BusinessPartnerGroup extends MDMMasterData implements IBusinessPartnerGroup,Serializable {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Groupcode")
    private Integer grpCode;

    @Column(name = "Groupname")
    private String grpName;

    @Column(name = "Grouptype")
    private String type;

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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
