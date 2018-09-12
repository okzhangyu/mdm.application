package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.ProfitCenter;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryProfitCenter extends CrudRepository<ProfitCenter,String> {

    /**
     * 查找成本中心
     * @param code 成本中心编码
     * @return
     */
    ProfitCenter findByPrcCode(String code);
}
