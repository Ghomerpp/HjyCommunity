package com.msb.hjycommunity.web.controller.community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb.hjycommunity.common.constant.HttpStatus;
import com.msb.hjycommunity.common.core.controller.BaseController;
import com.msb.hjycommunity.common.core.domain.BaseResponse;
import com.msb.hjycommunity.common.core.page.PageResult;
import com.msb.hjycommunity.common.utils.ServletUtils;
import com.msb.hjycommunity.community.domain.HjyCommunity;
import com.msb.hjycommunity.community.domain.dto.HjyCommunityDto;
import com.msb.hjycommunity.community.service.HjyCommunityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小区模块的控制层，接收前端的 HTTP 请求（比如/community/add新增小区），调用service层方法，最后返回BaseResponse格式的响应。
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/24 - 11 - 24 - 15:06
 * Description: com.msb.hjycommunity.web.controller
 */

@RestController
@RequestMapping("/community")
public class HjyCommunityController extends BaseController {


    //这是 Java 的依赖注入注解（也可以用 Spring 的@Autowired），作用是自动获取HjyCommunityService接口的实现类（HjyCommunityServiceImpl）对象，无需手动new对象。
    @Resource
    private HjyCommunityService hjyCommunityService;


    //多条件分页查询
    @GetMapping("/list")
    public PageResult list(HjyCommunity hjyCommunity){

        startPage();
        List<HjyCommunityDto> list = hjyCommunityService.queryList(hjyCommunity);
        return getData(list);
    }

    //新增小区
    @PostMapping
    public BaseResponse add(@RequestBody HjyCommunity hjyCommunity){
        return toAjax(hjyCommunityService.insertHjyCommunity(hjyCommunity));
    }

    @GetMapping("/{communityId}")
    //@PathVariable作用是：将 URL 路径中 占位符 {xxx} 的值，获取出来并赋值给当前方法的参数。
    public BaseResponse getInfo(@PathVariable("communityId") Long communityId){
        return BaseResponse.success(hjyCommunityService.selectHjyCommunityById(communityId));
    }

    @PutMapping
    public BaseResponse edit(@RequestBody HjyCommunity hjyCommunity){
        return toAjax(hjyCommunityService.updateHjyCommunity(hjyCommunity));
    }

    @DeleteMapping("/{communityIds}")
    public BaseResponse delete(@PathVariable Long[] communityIds){
        return toAjax(hjyCommunityService.deleteHjyCommunity(communityIds));
    }
}
