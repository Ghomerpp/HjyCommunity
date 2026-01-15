package com.msb.hjycommunity.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 基类(所有数据库实体类的 “父类”)
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/12 - 11 - 12 - 17:44
 * Description: com.msb.hjycommunity.common.core.domain
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 4494898107172639564L;

    /*搜索值*/
    @TableField(exist = false)  //表示不属于数据库字段 ,mybatis在新增的时候会忽略该字段
    private String searchValue;

    /*请求的参数*/
    @TableField(exist = false)
    private Map<String, Objects> params;

    /*创建者*/
    @TableField(fill = FieldFill.INSERT) //标明该字段在什么时候需要被填充
    private String createBy;

    /*创建的时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /*更新者*/
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /*更新的时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;

    /*备注*/
    private String remark;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map<String, Objects> getParams() {
        return params;
    }

    public void setParams(Map<String, Objects> params) {
        this.params = params;
    }

    public String getCreatBy() {
        return createBy;
    }

    public void setCreatBy(String creatBy) {
        this.createBy = createBy;
    }

    public Date getCreatTime() {
        return createTime;
    }

    public void setCreatTime(Date creatTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
