package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.ProfitCenterType;
import org.springframework.data.repository.CrudRepository;

public interface IBORepositoryProfitCenterType extends CrudRepository<ProfitCenterType,String> {

    /**
     * 获取成本中心类型
     * @param uniqueKey 主键
     * @return
     */
    ProfitCenterType getProfitCenterTypeByUniqueKey(String uniqueKey);
}
