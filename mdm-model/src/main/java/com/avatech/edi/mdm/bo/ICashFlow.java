package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

public interface ICashFlow extends IMDMMasterData {

    String getCashFlowCode();

    void setCashFlowCode(String cashFlowCode);

    String getCashFlowName();

    void setCashFlowName(String cashFlowName);

}
