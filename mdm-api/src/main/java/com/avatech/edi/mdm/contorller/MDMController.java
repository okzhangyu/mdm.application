package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.bo.IAccount;
import com.avatech.edi.mdm.config.ServiceParam;
import com.avatech.edi.mdm.data.ArrayList;
import com.avatech.edi.mdm.data.List;
import com.avatech.edi.mdm.dto.MasterData;
import com.avatech.edi.mdm.dto.SyncResult;
import com.avatech.edi.mdm.dto.Result;
import com.avatech.edi.mdm.repository.IBORepositoryAccount;
import com.avatech.edi.mdm.service.AbsMasterDataService;
import com.avatech.edi.mdm.service.BaseMasterDataService;
import com.avatech.edi.mdm.service.MasterDataServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@RestController
@RequestMapping("mdm/v1/*")
public class MDMController {

    @Autowired
    private MasterDataServiceFactory masterDataServiceFactory;

    @RequestMapping(value = "b1/masterdata",method ={RequestMethod.POST})
    public @ResponseBody Result postMasterData( //@RequestParam(ServiceParam.TOKEN) String token,
                                  @RequestBody MasterData mdmMasterData){

        BaseMasterDataService service = masterDataServiceFactory.getServiceInstance(mdmMasterData);
        List<SyncResult> results = service.syncMasterData(mdmMasterData);
        return Result.ok(results);
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
