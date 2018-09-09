package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.BusinessPartner;
import com.avatech.edi.mdm.bo.IBusinessPartner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fancy
 * @date 2018/9/6
 */
@Repository
public interface IBORepositoryBusinessPartner extends CrudRepository<BusinessPartner,String> {

    /**
     * 查找业务伙伴
     * @param cardCode 业务伙伴编码
     * @return
     */
    BusinessPartner findByCardCode(String cardCode);

}
