package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IAccount;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryAccount {

    /**
     * 查找科目
     * @param code 科目代码
     * @return
     */
    IAccount fetchAccount(String code);
}
