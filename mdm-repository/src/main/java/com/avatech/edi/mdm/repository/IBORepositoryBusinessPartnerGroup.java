package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.BusinessPartnerGroup;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryBusinessPartnerGroup extends CrudRepository<BusinessPartnerGroup,String> {

    /**
     * 查找业务伙伴组
     * @param uniqueKey 主键
     * @return
     */
    BusinessPartnerGroup findBusinessPartnerGroupByUniqueKey(String uniqueKey);

}
