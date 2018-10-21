package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.BillOfMaterial;
import com.avatech.edi.mdm.bo.CompontOfMaterialListItem;
import com.avatech.edi.mdm.bo.IBillOfMaterial;
import com.avatech.edi.mdm.bo.ICompontOfMaterialListItem;
import com.avatech.edi.mdm.config.ServiceException;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.BillOfMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bom/v1/*")
public class BOMController {

    private final Logger logger = LoggerFactory.getLogger(BOMController.class);

    @Autowired
    private BillOfMaterialService billOfMaterialService;

    @GetMapping("bom")
    public IBillOfMaterial getBillOfMaterial(){
        IBillOfMaterial bom = new BillOfMaterial();
        CompontOfMaterialListItem compontOfMaterialListItem = new CompontOfMaterialListItem();
        compontOfMaterialListItem.setBOMVer("1");
        compontOfMaterialListItem.setItemCode("10001");
        bom.setCompanyDB("TEST1");
        bom.setActived("Y");
        bom.getCompontOfMaterialListItems().add(compontOfMaterialListItem);
        return  bom;
    }


    @RequestMapping(value = "bomversion",method ={RequestMethod.POST})
    public @ResponseBody Result postBOMVersion( //@RequestParam(ServiceParam.TOKEN) String token,
                           @RequestBody BillOfMaterial bom){
        Result rt;
        try
        {
            logger.info("接收BOM信息》》》》》"+ bom.toString());
            if(bom.getProject() == null || bom.getProject().isEmpty())
                rt =  Result.error("1001","项目信息为空");
            else if(bom.getWorkOrderNo() == null || bom.getWorkOrderNo().isEmpty())
                rt = Result.error("1002","工单号信息为空");
            else {
                SyncResult results = billOfMaterialService.syncBOM(bom);
                rt = Result.ok(results);
            }
        }catch (ServiceException e){
            logger.error(bom.toString(),e);
            rt = Result.error("-1",e);
        }
        catch (Exception e){
            logger.error(bom.toString(),e);
            rt = Result.error("-1",e);
        }
        logger.info("BOM推送返回信息》》》》》"+ rt.toString());
        return rt;
    }
}
