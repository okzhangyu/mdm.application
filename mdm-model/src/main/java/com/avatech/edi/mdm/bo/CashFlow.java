package com.avatech.edi.mdm.bo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CashFlow extends MDMMasterData implements ICashFlow ,Serializable {

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

    private Integer cashFlowCode;

    private String cashFlowName;

    @Override
    public Integer getCashFlowCode() {
        return cashFlowCode;
    }

    @Override
    public void setCashFlowCode(Integer cashFlowCode) {
        this.cashFlowCode = cashFlowCode;
    }

    @Override
    public String getCashFlowName() {
        return cashFlowName;
    }

    @Override
    public void setCashFlowName(String cashFlowName) {
        this.cashFlowName = cashFlowName;
    }
}
