package com.avatech.edi.mdm.bo;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface  IBusinessPartner extends IMDMMasterData {

    String getCardCode();

    void setCardCode(String cardCode);

    String getCardName();

    void setCardName(String cardName);

    String getCardType();

    void setCardType(String cardType);

    Integer getGroupCode();

    void setGroupCode(Integer groupCode);

    String getCurrency();

    void setCurrency(String currency);
}
