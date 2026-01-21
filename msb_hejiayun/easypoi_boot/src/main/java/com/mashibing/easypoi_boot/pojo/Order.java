package com.mashibing.easypoi_boot.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@ExcelTarget("order")
@Data
@AllArgsConstructor
public class Order implements Serializable {

    @Excel(name = "订单编号")
    private String id;

    @Excel(name = "订单名称")
    private String name;
}
