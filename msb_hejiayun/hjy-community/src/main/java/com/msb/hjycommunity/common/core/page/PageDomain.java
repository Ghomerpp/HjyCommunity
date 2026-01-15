package com.msb.hjycommunity.common.core.page;

/**
 * 封装分页查询参数（比如前端传的pageNum（页码）、pageSize（每页条数））
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/24 - 11 - 24 - 14:33
 * Description: com.msb.hjycommunity.common.core.page
 */
public class PageDomain {
    /*当前记录起使索引*/
    private Integer pageNum;

    /*每页显示的记录数*/
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
