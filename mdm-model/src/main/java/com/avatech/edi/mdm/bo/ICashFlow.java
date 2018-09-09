package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

public interface ICashFlow extends IMDMMasterData {

    Integer getCashFlowCode();

    void setCashFlowCode(Integer cashFlowCode);

    String getCashFlowName();

    void setCashFlowName(String cashFlowName);

}
