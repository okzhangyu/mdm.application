package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public class Account extends MDMMasterData implements IAccount {

    private String acctCode;

    @Override
    public String getAcctCode() {
        return acctCode;
    }

    @Override
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    private String acctName;
    @Override
    public String getAcctName() {
        return acctName;
    }

    @Override
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    private Integer levels;

    @Override
    public Integer getLevels() {
        return levels;
    }

    @Override
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    private String locManTran;
    @Override
    public String getLocManTran() {
        return locManTran;
    }

    @Override
    public void setLocManTran(String locManTran) {
        this.locManTran = locManTran;
    }

    private String postable;
    @Override
    public String getPostable() {
        return postable;
    }

    @Override
    public void setPostable(String postable) {
        this.postable = postable;
    }

    private String acctntCod;
    @Override
    public String getAcctntCod() {
        return acctntCod;
    }

    @Override
    public void setAcctntCod(String acctntCod) {
        this.acctntCod = acctntCod;
    }

    private String actCur;
    @Override
    public String getActCur() {
        return actCur;
    }

    @Override
    public void setActCur(String actCur) {
        this.actCur = actCur;
    }

    private String fatherAccountKey;

    @Override
    public String getFatherAccountKey() {
        return fatherAccountKey;
    }

    private String finase;

    @Override
    public String getFinase() {
        return finase;
    }

    @Override
    public void setFinase(String finase) {
        this.finase = finase;
    }

    @Override
    public void setFatherAccountKey(String fatherAccountKey) {
        this.fatherAccountKey = fatherAccountKey;
    }

    private String cfwRlvnt;

    @Override
    public String getCfwRlvnt() {
        return cfwRlvnt;
    }

    @Override
    public void setCfwRlvnt(String cfwRlvnt) {
        this.cfwRlvnt = cfwRlvnt;
    }

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
