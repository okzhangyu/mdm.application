package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.MDMMasterData;

public class CashFlow extends MDMMasterData implements ICashFlow {

    private String cashFlowCode;

    private String cashFlowName;

    @Override
    public String getCashFlowCode() {
        return cashFlowCode;
    }

    @Override
    public void setCashFlowCode(String cashFlowCode) {
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
