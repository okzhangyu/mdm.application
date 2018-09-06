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
}
