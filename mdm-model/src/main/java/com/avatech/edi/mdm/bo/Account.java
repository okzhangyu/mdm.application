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
@Table(name = "AVA_MDM_VIEW_OACT")
public class Account extends MDMMasterData implements IAccount {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Column(name = "Acctcode")
    private String acctCode;

    @Override
    public String getAcctCode() {
        return acctCode;
    }

    @Override
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    @Column(name = "Acctname")
    private String acctName;
    @Override
    public String getAcctName() {
        return acctName;
    }

    @Override
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @Column(name = "Levels")
    private Integer levels;

    @Override
    public Integer getLevels() {
        return levels;
    }

    @Override
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    @Column(name = "Locmantran")
    private String locManTran;
    @Override
    public String getLocManTran() {
        return locManTran;
    }

    @Override
    public void setLocManTran(String locManTran) {
        this.locManTran = locManTran;
    }

    @Column(name = "Postable")
    private String postable;
    @Override
    public String getPostable() {
        return postable;
    }

    @Override
    public void setPostable(String postable) {
        this.postable = postable;
    }

    @Column(name = "Accntntcod")
    private String acctntCod;

    @Override
    public String getAcctntCod() {
        return acctntCod;
    }

    @Override
    public void setAcctntCod(String acctntCod) {
        this.acctntCod = acctntCod;
    }

    @Column(name = "Actcurr")
    private String actCur;

    @Override
    public String getActCur() {
        return actCur;
    }

    @Override
    public void setActCur(String actCur) {
        this.actCur = actCur;
    }

    @Column(name ="Fathernum")
    private String fatherAccountKey;

    @Override
    public String getFatherAccountKey() {
        return fatherAccountKey;
    }

    @Override
    public void setFatherAccountKey(String fatherAccountKey) {
        this.fatherAccountKey = fatherAccountKey;
    }

    @Column(name = "Finanse")
    private String finase;

    @Override
    public String getFinase() {
        return finase;
    }

    @Override
    public void setFinase(String finase) {
        this.finase = finase;
    }

    @Column(name = "Cfwrlvnt")
    private String cfwRlvnt;

    @Override
    public String getCfwRlvnt() {
        return cfwRlvnt;
    }

    @Override
    public void setCfwRlvnt(String cfwRlvnt) {
        this.cfwRlvnt = cfwRlvnt;
    }

    @Column(name = "Acttype")
    private String actType;

    @Override
    public String getActType() {
        return actType;
    }

    @Override
    public void setActType(String actType) {
        this.actType = actType;
    }
}
