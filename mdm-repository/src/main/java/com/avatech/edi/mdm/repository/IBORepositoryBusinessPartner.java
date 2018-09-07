package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IBusinessPartner;

/**
 * @author Fancy
 * @date 2018/9/6
 */
public interface IBORepositoryBusinessPartner {

    /**
     * 查找业务伙伴
     * @param cardCode 业务伙伴编码
     * @return
     */
    IBusinessPartner fetchBusinessPartner(String cardCode);

}
