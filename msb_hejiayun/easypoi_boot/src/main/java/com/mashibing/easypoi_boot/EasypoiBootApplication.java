package com.mashibing.easypoi_boot;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.mashibing.easypoi_boot.pojo.Card;
import com.mashibing.easypoi_boot.pojo.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.boot.SpringApplication;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class EasypoiBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoiBootApplication.class,args);
    }
}
