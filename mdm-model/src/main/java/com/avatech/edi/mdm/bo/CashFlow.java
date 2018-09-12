package com.avatech.edi.mdm.bo;

public class CashFlow extends MDMMasterData implements ICashFlow {

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
