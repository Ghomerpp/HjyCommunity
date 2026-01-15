package com.msb.hjycommunity.system.service;

import com.msb.hjycommunity.system.domain.SysArea;
import com.msb.hjycommunity.system.domain.dto.SysAreaDto;

import java.util.List;

/**
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/28 - 12 - 28 - 16:11
 * Description: com.msb.hjycommunity.system.service
 */
public interface SysAreaService {

    //获取区域数据的完整数
    List<SysAreaDto> findAreaAsTree();
}
