package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

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

    String getGroupCode();

    void setGroupCode(String groupCode);

}
