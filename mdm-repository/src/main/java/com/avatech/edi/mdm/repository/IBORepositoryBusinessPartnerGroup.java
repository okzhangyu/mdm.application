package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IBusinessPartnerGroup;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryBusinessPartnerGroup {

    /**
     * 查找业务伙伴组
     * @param code 组代码
     * @return
     */
    IBusinessPartnerGroup fetchBPGroup(String code);

}
