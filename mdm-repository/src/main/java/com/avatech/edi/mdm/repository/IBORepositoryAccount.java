package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryAccount extends CrudRepository<Account,String> {

    /**
     * 查找科目
     * @param uniqueKey 科目代码
     * @return
     */
    Account findAccountByUniqueKey(String uniqueKey);
}
