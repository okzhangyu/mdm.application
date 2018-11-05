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
    private Integer groupCode;

    @Override
    public Integer getGroupCode() {
        return groupCode;
    }

    @Override
    public void setGroupCode(Integer groupCode) {
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

    @Override
    public String getLicTradNum() {
        return null;
    }

    @Override
    public void setLicTradNum(String licTradNum) {

    }

    @Column(name = "Federaltaxid")
    private String federalTaxID;

    @Override
    public String getFederalTaxID() {
        return federalTaxID;
    }

    @Override
    public void setFederalTaxID(String federalTaxID) {
        this.federalTaxID = federalTaxID;
    }

    @Column(name="Gsbh")
    private String gsbh;

    @Column(name="Gnw")
    private String gnw;

    @Column(name = "Xtnw")
    private String xtnw;

    @Column(name = "Jspbm")
    private String jspbm;

    @Column(name = "Cj")
    private String cj;

    @Override
    public String getGsbh() {
        return gsbh;
    }

    @Override
    public void setGsbh(String gsbh) {
        this.gsbh = gsbh;
    }

    @Override
    public String getGnw() {
        return gnw;
    }

    @Override
    public void setGnw(String gnw) {
        this.gnw = gnw;
    }

    @Override
    public String getXtnw() {
        return xtnw;
    }

    @Override
    public void setXtnw(String xtnw) {
        this.xtnw = xtnw;
    }

    public String getJSPBM() {
        return jspbm;
    }

    public void setJSPBM(String jsbpm) {
        this.jspbm = jsbpm;
    }

    @Override
    public String getCj() {
        return cj;
    }

    @Override
    public void setCj(String cj) {
        this.cj = cj;
    }
}
