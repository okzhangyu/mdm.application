package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.dto.MasterData;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.service.BaseMasterDataService;
import com.avatech.edi.mdm.service.MasterDataServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Fancy
 * @date 2018/9/4
 */
@RestController
@RequestMapping("mdm/v1/*")
public class MDMController {

    private final Logger logger = LoggerFactory.getLogger(MDMController.class);
    @Autowired
    private MasterDataServiceFactory masterDataServiceFactory;

    @RequestMapping(value = "b1/masterdata",method ={RequestMethod.POST})
    public @ResponseBody Result postMasterData( //@RequestParam(ServiceParam.TOKEN) String token,
                           @RequestBody MasterData mdmMasterData){
        Result rt;
        try
        {
            logger.info("接收主数据信息》》》》》"+ mdmMasterData.toString());
            BaseMasterDataService service = masterDataServiceFactory.getServiceInstance(mdmMasterData);
            List<SyncResult> results = service.syncMasterData(mdmMasterData);
            rt = Result.ok(results);

        }catch (Exception e){
            logger.error(mdmMasterData.toString(),e);
            rt = Result.error("-1",e);
        }
        logger.info("主数据推送返回信息》》》》》"+ rt.toString());
        return rt;
    }


    @GetMapping("b1/masterdata")
    public Result getMasterData(){
        MasterData mdmMasterData = new MasterData();
        mdmMasterData.setObjectCode("1");
        mdmMasterData.setSourceCompany("SUN");
        mdmMasterData.setSourceServer("192.168.1.13");
        mdmMasterData.setTargetCompany("MOON");
        mdmMasterData.setTargetServer("192.168.1.14");
        mdmMasterData.setTenantCode("AVA");
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        mdmMasterData.setData(data);
        return Result.ok(mdmMasterData);
    }
}
