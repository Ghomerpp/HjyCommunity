package com.msb.hjycommunity.community.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.msb.hjycommunity.common.core.domain.BaseEntity;

/**
 * 数据传输对象
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/18 - 11 - 18 - 18:15
 * Description: com.msb.hjycommunity.community.domain.dto
 */

//跨层 / 跨服务传输小区数据的容器，按需补充字段，和数据库无直接绑定
//DTO 只负责 “传数据”，不参与数据库操作，和数据库表结构解耦。
//HjyCommunityDto是接收前端参数 / 服务间传递数据的对象
public class HjyCommunityDto extends BaseEntity {
    private static final long serialVersionUID = -5116423231864830282L;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long communityId;

    /*小区名称*/
    private String communityName;

    /*小区编码*/
    private String communityCode;

    /*省区划码*/
    private String communitProvinceCode;

    //省名称
    private String communitProvinceName;

    /*市区划码*/
    private String communityCityCode;

    /*市名称*/
    private String communityCityName;

    /*区县划码*/
    private String communityTownCode;

    /*区县名称*/
    private String communityTownName;

    /*详细地址*/
    private String communityDetailedAddress;

    /*经度*/
    private String Longitude;

    /*纬度*/
    private String Latitude;

    /*物业ID*/
    private Long deptId;

    /*排序*/
    private Long communitySort;

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

    public String getCommunitProvinceCode() {
        return communitProvinceCode;
    }

    public void setCommunitProvinceCode(String communitProvinceCode) {
        this.communitProvinceCode = communitProvinceCode;
    }

    public String getCommunityCityCode() {
        return communityCityCode;
    }

    public void setCommunityCityCode(String communityCityCode) {
        this.communityCityCode = communityCityCode;
    }

    public String getCommunityTownCode() {
        return communityTownCode;
    }

    public void setCommunityTownCode(String communityTownCode) {
        this.communityTownCode = communityTownCode;
    }

    public String getCommunityDetailedAddress() {
        return communityDetailedAddress;
    }

    public void setCommunityDetailedAddress(String communityDetailedAddress) {
        this.communityDetailedAddress = communityDetailedAddress;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCommunitySort() {
        return communitySort;
    }

    public void setCommunitySort(Long communitySort) {
        this.communitySort = communitySort;
    }
}
