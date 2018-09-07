package com.avatech.edi.mdm.repository.hana;

import com.avatech.edi.mdm.bo.Account;
import com.avatech.edi.mdm.bo.IAccount;
import com.avatech.edi.mdm.repository.IBORepositoryAccount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Repository
public class BORepositoryAccount implements IBORepositoryAccount {
    @Override
    public IAccount fetchAccount(String code) {
        IAccount account = new Account();
        account.setAcctCode("1001");
        account.setAcctName("银行转账");
        account.setActCur("RMB");
        account.setLevels(new Integer(2));
        return account;
    }
}
