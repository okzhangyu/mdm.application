package com.avatech.edi.mdm.bo;

import com.avatech.edi.mdm.IMDMMasterData;

/**
 * @author Fancy
 * @date 2018/9/4
 */
public interface  IBusinessPartner extends IMDMMasterData {

    String getCode();

    void setCode(String code);
}
