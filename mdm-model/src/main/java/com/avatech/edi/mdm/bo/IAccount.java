package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface IAccount extends IMDMMasterData {

    String getAcctCode();

    void setAcctCode(String acctCode);

    String getAcctName();

    void setAcctName(String acctName);

    Integer getLevels();

    void setLevels(Integer levels);

    String getLocManTran();

    void setLocManTran(String locManTran);

    String getPostable();

    void setPostable(String postable);

    String getAcctntCod();

    void setAcctntCod(String acctntCod);

    String getActCur();

    void setActCur(String actCur);
}
