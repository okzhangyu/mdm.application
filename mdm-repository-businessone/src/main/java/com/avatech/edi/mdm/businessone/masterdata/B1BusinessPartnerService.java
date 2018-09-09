package com.avatech.edi.mdm.businessone.masterdata;

import com.avatech.edi.mdm.bo.IBusinessPartner;
import com.avatech.edi.mdm.config.B1Connection;

public interface B1BusinessPartnerService {

    String syncBusinessPartner(IBusinessPartner businessPartner, B1Connection b1Connection);
}
