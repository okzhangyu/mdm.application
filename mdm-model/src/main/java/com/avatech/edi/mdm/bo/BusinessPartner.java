package com.avatech.edi.mdm.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@Entity
@Table(name = "AVA_MDM_VIEW_OCRD")
public class BusinessPartner extends MDMMasterData implements IBusinessPartner,Serializable {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

    @Override
    public String getUniqueKey() {
        return uniqueKey;
    }

    @Override
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Column(name = "Cardcode")
    private String cardCode;

    @Override
    public String getCardCode() {
        return cardCode;
    }

    @Override
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    @Column(name = "Cardname")
    private String cardName;
    @Override
    public String getCardName() {
        return cardName;
    }

    @Override
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Column(name = "Cardtype")
    private String cardType;

    @Override
    public String getCardType() {
        return cardType;
    }

    @Override
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name = "Groupcode")
    private String groupCode;

    @Override
    public String getGroupCode() {
        return groupCode;
    }

    @Override
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "Currency")
    private String currency;

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
