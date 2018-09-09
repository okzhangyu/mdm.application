package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@Entity
@Table(name = "AVA_MDM_VIEW_OCRD")
public class BusinessPartner extends MDMMasterData implements IBusinessPartner {

    @Id
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
}
