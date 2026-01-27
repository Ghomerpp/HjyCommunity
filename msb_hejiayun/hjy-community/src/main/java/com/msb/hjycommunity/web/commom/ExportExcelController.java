package com.msb.hjycommunity.web.commom;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.msb.hjycommunity.common.core.controller.BaseController;
import com.msb.hjycommunity.common.core.domain.BaseResponse;
import com.msb.hjycommunity.common.utils.ExcelUtils;
import com.msb.hjycommunity.community.domain.HjyCommunity;
import com.msb.hjycommunity.community.domain.dto.HjyCommunityDto;
import com.msb.hjycommunity.community.domain.dto.HjyCommunityExcelDto;
import com.msb.hjycommunity.community.service.HjyCommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exportExcel")
public class ExportExcelController extends BaseController {

    @Resource
    private HjyCommunityService communityService;

    /**
     * 导出小区数据
     * @param hjyCommunity
     * @param response
     * @return
     */

    @GetMapping("/exportCommunityExcel")
    public BaseResponse exportExcel(HjyCommunity hjyCommunity, HttpServletResponse response){

        startPage();
        List<HjyCommunityDto> list = communityService.queryList(hjyCommunity);

        //数据转换
        List<HjyCommunityExcelDto> excelDtoList = list.stream().map(hjyCommunityDto -> {
            HjyCommunityExcelDto excelDto = new HjyCommunityExcelDto();
            excelDto.setCommunityId(hjyCommunityDto.getCommunityId());
            excelDto.setCommunityName(hjyCommunityDto.getCommunityName());
            excelDto.setCommunityCode(hjyCommunityDto.getCommunityCode());
            excelDto.setCommunitProvinceName(hjyCommunityDto.getCommunitProvinceName());
            excelDto.setCommunityCityName(hjyCommunityDto.getCommunityCityName());
            excelDto.setCommunityTownName(hjyCommunityDto.getCommunityTownName());
            excelDto.setCreatTime(hjyCommunityDto.getCreatTime());
            excelDto.setRemark(hjyCommunityDto.getRemark());

            return excelDto;
        }).collect(Collectors.toList());
        ExcelUtils.exportExcel(excelDtoList, HjyCommunityExcelDto.class,"小区信息.xls",response,
                new ExportParams("小区信息列表","小区信息"));

        return BaseResponse.success("导出小区信息成功到Excel成功！");
    }
}
