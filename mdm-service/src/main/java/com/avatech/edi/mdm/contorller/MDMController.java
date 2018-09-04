package com.avatech.edi.mdm.contorller;

import com.avatech.edi.mdm.dto.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fancy
 * @date 2018/9/4
 */
@RestController
@RequestMapping("mdm/v1/*")
public class MDMController {

    @PostMapping("b1/masterdata")
    public Result postMasterData(){
        return Result.ok();
    }
}
