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

    String getLicTradNum();
    void setLicTradNum(String licTradNum);

    String getFederalTaxID();

    void setFederalTaxID(String federalTaxID);

    String getGsbh();
    void setGsbh(String gsbh);

    String getGnw();
    void setGnw(String gnw);

    String getXtnw();
    void setXtnw(String xtnw);

    String getCj();
    void setCj(String cj);

    String getJSPBM();
    void setJSPBM(String jspbm);
}
