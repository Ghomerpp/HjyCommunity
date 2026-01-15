package com.msb.hjycommunity.community.service.impl;

import com.msb.hjycommunity.community.domain.HjyCommunity;
import com.msb.hjycommunity.community.domain.dto.HjyCommunityDto;
import com.msb.hjycommunity.community.mapper.HjyCommunityMapper;
import com.msb.hjycommunity.community.service.HjyCommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 接口的实现类，写具体的业务逻辑
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/19 - 11 - 19 - 10:51
 * Description: com.msb.hjycommunity.community.service.impl
 */
@Service
public class HjyCommunityServiceImpl implements HjyCommunityService {

    //@Resource 是让 Spring 框架帮你「自动找对象、给你用」，不用自己手动创建 HjyCommunityMapper 对象。
    @Resource
    private HjyCommunityMapper hjyCommunityMapper;

    private static final String CODE_PREFIX = "COMMUNITY_";

    @Override
    public List<HjyCommunityDto> queryList(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.queryList(hjyCommunity);
    }

    @Override
    public int insertHjyCommunity(HjyCommunity hjyCommunity) {
        //设置一个小区的编码
        hjyCommunity.setCommunityCode(CODE_PREFIX + System.currentTimeMillis());
        return hjyCommunityMapper.insert(hjyCommunity);
    }

    @Override
    public HjyCommunity selectHjyCommunityById(Long communityId) {
        return hjyCommunityMapper.selectById(communityId);
    }

    @Override
    public int updateHjyCommunity(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.updateById(hjyCommunity);
    }

    @Override
    public int deleteHjyCommunity(Long[] communityIds) {
        return hjyCommunityMapper.deleteBatchIds(Arrays.asList(communityIds));
    }

}
