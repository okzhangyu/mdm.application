package com.avatech.edi.mdm.bo;

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

    /**
     * 获取-是否现金科目[是/否]
     * @return
     */
    String getFinase();

    /**
     * 获取-是否现金科目[是/否]
     * @param finase
     */
    void setFinase(String finase);

    String getLocManTran();

    void setLocManTran(String locManTran);

    /**
     * 获取-科目[活动/标题]
     * @return
     */
    String getPostable();

    /**
     * 获取-科目[活动/标题]
     * @param postable
     */
    void setPostable(String postable);

    String getAcctntCod();

    void setAcctntCod(String acctntCod);

    /**
     * 获取-父项id
     * @return
     */
    String getFatherAccountKey();

    /**
     * 设置-父项id
     * @param fatherNum
     */
    void setFatherAccountKey(String fatherNum);

    /**
     * 获取-现金流相关[是/否]
     * @return
     */
    String getCfwRlvnt();

    /**
     * 设置-现金流相关[是/否]
     * @param cfwRlvnt
     */
    void setCfwRlvnt(String cfwRlvnt);

    /**
     * 获取-科目币种
     * @return
     */
    String getActCur();

    /**
     * 设置-科目币种
     * @param actCur
     */
    void setActCur(String actCur);

    /**
     * 获取-科目类别
     * @return
     */
    String getActType();

    /**
     * 设置-科目类别
     * @param actType
     */
    void setActType(String actType);
}
