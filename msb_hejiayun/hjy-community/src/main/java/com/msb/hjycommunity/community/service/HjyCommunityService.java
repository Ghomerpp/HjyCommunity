package com.msb.hjycommunity.community.service;

import com.msb.hjycommunity.community.domain.HjyCommunity;
import com.msb.hjycommunity.community.domain.dto.HjyCommunityDto;

import java.util.List;

/**
 * 小区模块的业务接口（定义 “新增小区”“查询小区列表” 等业务方法）；
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/19 - 11 - 19 - 10:49
 * Description: com.msb.hjycommunity.community.service
 */
public interface HjyCommunityService {

    /*根据条件查询小区信息列表 */
    List<HjyCommunityDto> queryList(HjyCommunity hjyCommunity);

    //新增小区信息
    int insertHjyCommunity(HjyCommunity hjyCommunity);

    //根据Id获取小区详情
    HjyCommunity selectHjyCommunityById(Long communityId);

    //修改小区
    int updateHjyCommunity(HjyCommunity hjyCommunity);

    //删除操作
    int deleteHjyCommunity(Long[] communityIds);
}
