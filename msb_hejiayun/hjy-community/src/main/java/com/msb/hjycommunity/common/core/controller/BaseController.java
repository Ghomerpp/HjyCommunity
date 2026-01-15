package com.msb.hjycommunity.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb.hjycommunity.common.constant.HttpStatus;
import com.msb.hjycommunity.common.core.domain.BaseResponse;
import com.msb.hjycommunity.common.core.page.PageDomain;
import com.msb.hjycommunity.common.core.page.PageResult;
import com.msb.hjycommunity.common.utils.ServletUtils;

import java.util.List;

/**
 * 基础控制器类(所有 Controller 的 “父类”，封装通用逻辑（比如统一响应、权限校验前置操作）；)
 * 这是一个项目自定义的通用控制器基类（BaseController），
 * 它的核心价值是 封装了分页功能的全套通用逻辑（分页参数获取、分页插件初始化、分页结果封装）
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/1 - 12 - 01 - 12:49
 * Description: com.msb.hjycommunity.common.core.controller
 */
public class BaseController {
    //定义前端传递的分页参数固定名称（pageNum= 当前页码，pageSize= 每页条数），作为全局常量使用。
    //避免硬编码：如果后续需要修改分页参数名（比如把pageNum改为pageIndex），只需修改这里的常量值，无需修改所有使用处，降低维护成本。
    //统一规范：强制所有业务控制器使用相同的分页参数名，避免团队成员各自定义参数名导致的混乱，便于前后端交互统一。

    /*当前记录的起始索引*/
    public static final String PAGE_NUM = "pageNum";

    /*每页显示记录数*/
    public static final String PAGE_SIZE = "pageSize";

    /*封装分页数据*/
    //作用：从前端 HTTP 请求中获取分页参数（pageNum和pageSize），并封装到PageDomain（分页参数封装对象）中，返回给后续方法使用。
    public static PageDomain getPageDomain(){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));

        return pageDomain;
    }

    /*封装调用PageHelper的startPage方法*/
    //核心作用：这是分页功能的 “开关”，用于初始化PageHelper（MyBatis 分页插件），让后续的 MyBatis 查询自动实现分页。
    //PageHelper 帮你做了什么？
    //PageHelper是 MyBatis 框架的第三方分页插件，核心作用是帮你 “自动完成分页逻辑”—— 不用手动写分页 SQL（比如LIMIT）、不用手动查总记录数，大幅简化 MyBatis 项目的分页查询代码。
    //它相当于一个 “MyBatis 的 SQL 拦截器”，自动帮你完成上述所有麻烦事，你只需要做1 件事：调用PageHelper.startPage(pageNum, pageSize)。
    protected void startPage(){
        PageDomain pageDomain = getPageDomain();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
    }

    /*响应分页数据*/
    //核心作用：将 MyBatis 分页后的原始数据列表，封装成前端能直接使用的分页结果对象（PageResult），统一分页响应格式。
    protected PageResult getData(List<?> list){
        PageResult pageResult = new PageResult();
        pageResult.setCode(HttpStatus.SUCCESS);
        pageResult.setMsg("分页查询成功");
        pageResult.setRows(list);
        pageResult.setTotal(new PageInfo(list).getTotal());

        return pageResult;
    }

    //响应返回结果(针对增删改 操作)
    //rows受影响的行数
    protected BaseResponse toAjax(int rows){
        return rows > 0 ? BaseResponse.success(rows) : BaseResponse.fail("操作失败");
    }
}
