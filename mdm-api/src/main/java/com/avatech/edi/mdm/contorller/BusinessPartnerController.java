package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.BusinessPartner;
import com.avatech.edi.mdm.bo.IBusinessPartner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@RestController
@RequestMapping("mdm/v1/*")
public class BusinessPartnerController {

    @GetMapping("businesspartners")
    public List<IBusinessPartner> getBusinessPartner(){
        List<IBusinessPartner> businessPartners = new ArrayList();
        IBusinessPartner businessPartner = new BusinessPartner();
        businessPartner.setCardCode("111");
        businessPartners.add(businessPartner);
        return  businessPartners;
    }
}
