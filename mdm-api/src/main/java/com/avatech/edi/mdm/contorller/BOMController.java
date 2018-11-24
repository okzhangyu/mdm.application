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

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("bom/v1/*")
public class BOMController {

    /**
     * 定义资源总数
     */
    Semaphore semaphore=new Semaphore(1);

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


    @RequestMapping(value = "bomversiontest",method ={RequestMethod.POST})
    public @ResponseBody Result postTest(@RequestBody BillOfMaterial bom){
        int availablePermits=semaphore.availablePermits();//可用资源数
        if(availablePermits>0){
            logger.info("抢到资源");
        }else{
            logger.info("资源已被占用，稍后再试");
            return Result.error("1100","BOM审批资源正在被使用，请稍后再试");
        }
        try {
            semaphore.acquire(1);  //请求占用一个资源
            logger.info("资源正在被使用");
            Thread.sleep(30000);//放大资源占用时间，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            semaphore.release(1);//释放一个资源
        }
        return Result.ok();
    }

    @RequestMapping(value = "bomversion",method ={RequestMethod.POST})
    public @ResponseBody Result postBOMVersion( //@RequestParam(ServiceParam.TOKEN) String token,
                           @RequestBody BillOfMaterial bom){
        Result rt;
        int availablePermits=semaphore.availablePermits();//可用资源数
        if(availablePermits<=0){
            logger.info("BOM审批资源已被占用");
            return Result.error("1100","BOM审批资源正在被使用，请稍后再试");
        }
        try
        {
            semaphore.acquire(1);  //请求占用一个资源
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
        }finally {
            semaphore.release(1);//释放一个资源
        }
        logger.info("BOM推送返回信息》》》》》"+ rt.toString());
        return rt;
    }
}
