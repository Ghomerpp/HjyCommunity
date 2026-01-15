package com.msb.hjycommunity.web.controller.system;

import com.msb.hjycommunity.common.core.controller.BaseController;
import com.msb.hjycommunity.common.core.domain.BaseResponse;
import com.msb.hjycommunity.system.service.SysAreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/29 - 12 - 29 - 19:16
 * Description: com.msb.hjycommunity.web.controller.system
 */

@RestController
@RequestMapping("/system/area")
public class SysAreaController extends BaseController {

    @Resource
    private SysAreaService sysAreaService;


    @RequestMapping("/tree")
    public BaseResponse getAreaTree(){
        return BaseResponse.success(sysAreaService.findAreaAsTree());
    }
}
