package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.BillOfMaterial;
import com.avatech.edi.mdm.bo.CompontOfMaterialListItem;
import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.bo.ICompontOfMaterialListItem;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.BillOfMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("approver/v1/*")
public class BOMController {

    private final Logger logger = LoggerFactory.getLogger(BOMController.class);

    @Autowired
    private BillOfMaterialService billOfMaterialService;

    @GetMapping("bom")
    public IBillOfMaterial getBillOfMaterial(){
        IBillOfMaterial bom = new BillOfMaterial();
        ICompontOfMaterialListItem compontOfMaterialListItem = new CompontOfMaterialListItem();
        compontOfMaterialListItem.setBOMVer("1");
        compontOfMaterialListItem.setItemCode("10001");
        bom.setCompanyName("TEST1");
        bom.setActived("Y");
        bom.getCompontOfMaterialListItems().add(compontOfMaterialListItem);
        return  bom;
    }


    @RequestMapping(value = "b1/bom",method ={RequestMethod.POST})
    public @ResponseBody Result postMasterData( //@RequestParam(ServiceParam.TOKEN) String token,
                           @RequestBody BillOfMaterial bom){
        Result rt;
        try
        {
            logger.info("接收BOM信息》》》》》"+ bom.toString());
            SyncResult results = billOfMaterialService.syncBOM(bom);
            rt = Result.ok(results);

        }catch (Exception e){
            logger.error(bom.toString(),e);
            rt = Result.error("-1",e);
        }
        logger.info("BOM推送返回信息》》》》》"+ rt.toString());
        return rt;
    }
}
