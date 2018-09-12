package com.avatech.edi.mdm.bo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CashFlow extends MDMMasterData implements ICashFlow ,Serializable {

    @Id
    @Column(name = "Uniquekey")
    private String uniqueKey;

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
