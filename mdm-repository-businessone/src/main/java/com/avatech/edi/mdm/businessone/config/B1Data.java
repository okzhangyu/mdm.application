package com.avatech.edi.mdm.businessone.config;

import com.sap.smb.sbo.api.SBOCOMConstants;

public class B1Data {

    public final static String YES = "Y";

    public final static String NO = "N";

    /**
     * 供应商
     */
    public final static String CUSTOMER = "C";

    public final static String SUPPLIER = "S";

    public final static String LEAD_CUSTOMER = "L";

    /**
     * 采购报价单
     */
    public static final Integer PURCHASEQUOTE = 540000006;

    public static final Integer TRANSFER_REQUEST = 1250000001;

    public static final Integer TRANSFER = 67;

    /**
     * 虚拟客户
     */
    public static final String VISUAL_CARDCODE = "C000999";

    /**
     * 虚拟供应商
     */
    public static final String VISUAL_SUPPLIER = "V000999";

    /**
     * 虚拟物料
     */
    public static final String VISUAL_ITEMCODE = "V000999";

    /**
     * 虚拟仓库
     */
    public static final String VISUAL_WHSCODE1 = "W999";

    /**
     * 虚拟仓库
     */
    public static final String VISUAL_WHSCODE2 = "W998";

}
