package com.msb.hjycommunity.community.domain.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

/*
* 导出Excel对应的实体*/
@ExcelTarget("community")
public class HjyCommunityExcelDto {

    /*小区ID*/
    @Excel(name = "序号")
    private Long communityId;

    /*小区名称*/
    @Excel(name = "小区名称")
    private String communityName;

    /*小区编码*/
    @Excel(name = "小区编码")
    private String communityCode;

    //省名称
    @Excel(name = "省")
    private String communitProvinceName;

    @Excel(name = "市")
    /*市名称*/
    private String communityCityName;

    /*区县名称*/
    @Excel(name = "区/县")
    private String communityTownName;

    @Excel(name = "创建时间",exportFormat = "yyyy年MM月dd日")
    private Date creatTime;

    @Excel(name = "备注")
    private String remark;


    public HjyCommunityExcelDto(Long communityId, String communityName, String communityCode, String communitProvinceName, String communityCityName, String communityTownName, Date creatTime, String remark) {
        this.communityId = communityId;
        this.communityName = communityName;
        this.communityCode = communityCode;
        this.communitProvinceName = communitProvinceName;
        this.communityCityName = communityCityName;
        this.communityTownName = communityTownName;
        this.creatTime = creatTime;
        this.remark = remark;
    }

    public HjyCommunityExcelDto() {
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getCommunitProvinceName() {
        return communitProvinceName;
    }

    public void setCommunitProvinceName(String communitProvinceName) {
        this.communitProvinceName = communitProvinceName;
    }

    public String getCommunityCityName() {
        return communityCityName;
    }

    public void setCommunityCityName(String communityCityName) {
        this.communityCityName = communityCityName;
    }

    public String getCommunityTownName() {
        return communityTownName;
    }

    public void setCommunityTownName(String communityTownName) {
        this.communityTownName = communityTownName;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
