package com.avatech.edi.mdm;

import com.avatech.edi.mdm.bo.IBOMasterData;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IMDMMasterData extends IBOMasterData{

    String getUniqueKey();

    void setUniqueKey(String uniqueKey);
}
