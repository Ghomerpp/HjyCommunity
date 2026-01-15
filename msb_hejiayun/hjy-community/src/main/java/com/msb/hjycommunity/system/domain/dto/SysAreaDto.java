package com.msb.hjycommunity.system.domain.dto;


import java.io.Serializable;
import java.util.List;

/**
 * 区域数据传输对象
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/28 - 12 - 28 - 15:38
 * Description: com.msb.hjycommunity.system.domain.dto
 */
public class SysAreaDto implements Serializable {

    private static final long serialVersionUID = -6455780937345574724L;

    //城市编码
    private Integer code;

    //城市名称
    private String name;

    //子区域
    private List<SysAreaDto> children;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysAreaDto> getChildren() {
        return children;
    }

    public void setChildren(List<SysAreaDto> children) {
        this.children = children;
    }
}
