package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.Account;
import com.avatech.edi.mdm.bo.IAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryAccount extends CrudRepository<Account,String> {

    /**
     * 查找科目
     * @param code 科目代码
     * @return
     */
    Account findByAcctCode(String code);
}
