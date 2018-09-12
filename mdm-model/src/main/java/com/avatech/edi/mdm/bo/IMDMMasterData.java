package com.avatech.edi.mdm.bo;

import com.avatech.edi.model.bo.IBOMasterData;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IMDMMasterData extends IBOMasterData {

    String getUniqueKey();

    void setUniqueKey(String uniqueKey);
}
