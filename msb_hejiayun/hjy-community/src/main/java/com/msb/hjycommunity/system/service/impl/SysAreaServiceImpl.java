package com.msb.hjycommunity.system.service.impl;

import com.msb.hjycommunity.system.domain.SysArea;
import com.msb.hjycommunity.system.domain.dto.SysAreaDto;
import com.msb.hjycommunity.system.mapper.SysAreaMapper;
import com.msb.hjycommunity.system.service.SysAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 核心功能是：将数据库中查询到的 “扁平区域列表”（省、市、区、街道的平铺数据），转换成前端需要的 “树形层级结构”
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/28 - 12 - 28 - 16:13
 * Description: com.msb.hjycommunity.system.service.impl
 */

@Service
public class SysAreaServiceImpl implements SysAreaService {

    @Resource
    private SysAreaMapper sysAreaMapper;

    @Override
    public List<SysAreaDto> findAreaAsTree() {

        //获取区域表数据
        //从数据库查询所有区域的扁平数据（平铺的省、市、区、街道，无层级关系）
        List<SysArea> list = sysAreaMapper.findAll();

        //流式编程：将扁平的SysArea列表，转换成树形的SysAreaDto列表
        return list.stream()   //把集合转化为流。   list.stream()：把普通的List<SysArea>变成 “流”，流就像 “流水线”，可以对数据进行批量处理（筛选、转换等），比普通for循环更简洁。
                .filter(area -> area.getParentCode().equals(0))    //筛选pid为0的area根节点对象;

                /*“转换” 操作，把一种对象（SysArea，数据库实体）转换成另一种对象（SysAreaDto，前端视图对象）。
                为什么要转换？因为SysArea可能包含数据库私有字段（比如id、createTime），
                前端不需要这些字段，用SysAreaDto只返回前端需要的code、name、children，
                更安全、更轻量化。*/
                .map(area -> {  //将area进行转换

                    // Lambda表达式：area 是流中传入的每一个SysArea对象（比如“北京市”“朝阳区”等）
                    // 步骤1：创建目标对象（SysAreaDto）—— 相当于准备一个空的成品包装盒
                    SysAreaDto sysAreaDto = new SysAreaDto();

                    // 步骤2：给目标对象赋值 —— 相当于把原材料（SysArea）的有用信息，装进成品包装盒
                    sysAreaDto.setCode(area.getCode()); // 把SysArea的编码，赋值给SysAreaDto
                    sysAreaDto.setName(area.getName()); // 把SysArea的名称，赋值给SysAreaDto
                    sysAreaDto.setChildren(getChildrenArea(sysAreaDto,list)); // 给Dto设置子节点（树形结构核心）

                    // 步骤3：返回目标对象 —— 相当于把装好的成品包装盒，送出工厂，进入下一个流程
                    return sysAreaDto;

                    /*把处理完的 “流” 再转换回List集合，因为方法返回值是List<SysAreaDto>，前端需要列表格式的数据。*/
                    /*.collect(Collectors.toList()) 是 Java Stream 流式编程的终止操作，
                    其中 Collectors.toList() 是专门用于 “收集流中元素” 的工具方法*/
                }).collect(Collectors.toList());
    }

    //设置递归区域信息
    //sysAreaDto 参数1：上级区域DTO（比如省级、市级区域，作为“父节点”）
    //list 参数2：所有区域的扁平数据（无需重复查询数据库，提升效率）
    private List<SysAreaDto> getChildrenArea(SysAreaDto sysAreaDto, List<SysArea> list) {
        List<SysArea> subAreaList = list.stream()
                // 筛选条件：子区域的parentCode（父编码） == 上级区域DTO的code（父区域编码）
                .filter(area -> area.getParentCode()
                .equals(sysAreaDto.getCode()))
                // 终止操作：将筛选后的流转换为List<SysArea>（子区域实体列表）
                .collect(Collectors.toList());


        // 分支1：存在子区域 → 继续转换+递归构建子区域的树形结构
        if(subAreaList != null && subAreaList.size() != 0){
            return subAreaList.stream()
                    .map(area ->{
                        //1 新建子区域DTO对象
                        SysAreaDto subAreaDto = new SysAreaDto();

                        //2 赋值：只保留前端需要的name和code（剔除实体冗余字段）
                        subAreaDto.setName(area.getName());
                        subAreaDto.setCode(area.getCode());

                        //3 递归调用：为当前子区域（比如市级）查找它的子节点（比如区级）
                        // 这里是“自己调用自己”，核心递归逻辑！
                        //设置子节点,递归调用直到获取到叶子节点为止
                        subAreaDto.setChildren(getChildrenArea(subAreaDto,list));

                        //4 返回转换后的子区域DTO（带自身子节点）
                        return subAreaDto;
            }).collect(Collectors.toList());// 收集：将转换后的子区域DTO流，转为List<SysAreaDto>并返回
        }else {
            // 分支2：不存在子区域 → 返回null，终止当前递归分支
            return null;
        }
    }
}
