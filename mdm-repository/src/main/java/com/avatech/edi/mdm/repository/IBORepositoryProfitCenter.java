package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IProfitCenter;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryProfitCenter {

    /**
     * 查找成本中心
     * @param code 成本中心编码
     * @return
     */
    IProfitCenter fetchCostCenter(String code);
}
