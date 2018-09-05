package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.config.ServiceParam;
import com.avatech.edi.mdm.data.ArrayList;
import com.avatech.edi.mdm.data.List;
import com.avatech.edi.mdm.dto.MDMMasterData;
import com.avatech.edi.mdm.dto.MDMSyncMsg;
import com.avatech.edi.mdm.dto.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@RestController
@RequestMapping("mdm/v1/*")
public class MDMController {

    @RequestMapping(value = "b1/masterdata",method ={RequestMethod.POST})
    public @ResponseBody Result postMasterData(@RequestParam(ServiceParam.TOKEN) String token,
                                  @RequestBody MDMMasterData mdmMasterData){





        List<MDMSyncMsg> mdmSyncMsgList = new ArrayList<>();
        MDMSyncMsg mdmSyncMsg = new MDMSyncMsg();
        mdmSyncMsg.setCode("0");
        mdmSyncMsg.setMessage("successful");
        mdmSyncMsg.setObjectKey("1");
        mdmSyncMsg.setReturnKey("2");
        mdmSyncMsgList.add(mdmSyncMsg);
        mdmSyncMsg = new MDMSyncMsg();
        mdmSyncMsg.setCode("10002");
        mdmSyncMsg.setMessage("failed");
        mdmSyncMsg.setObjectKey("2");
        mdmSyncMsg.setReturnKey("3");
        mdmSyncMsgList.add(mdmSyncMsg);
        return Result.ok(mdmSyncMsgList);
    }


    @GetMapping("b1/masterdata")
    public Result getMasterData(){
        MDMMasterData mdmMasterData = new MDMMasterData();
        mdmMasterData.setObjectCode("4");
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

        return Result.ok(mdmMasterData);
    }
}
