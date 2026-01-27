package com.mashibing.easypoi_boot.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.mashibing.easypoi_boot.entity.Course;
import com.mashibing.easypoi_boot.service.CourseService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Course> courseList = courseService.findAll();
        System.out.println(courseList);
        model.addAttribute("courses",courseList);

        return "index";
    }

    //导入excel
    @RequestMapping("importExcel")
    /*MultipartFile excelFile：Spring MVC 提供的文件上传接收参数
    → 前端上传的 Excel 文件会被封装成这个对象，是处理文件上传的核心类（不用自己解析文件流，Spring 已经封装好）；*/
    public String importExcel(MultipartFile excelFile) throws Exception {

        //创建 EasyPoi 的导入配置对象，所有 Excel 解析规则都通过它配置；
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        //excelFile.getInputStream()：从上传的文件对象中获取文件输入流（EasyPoi 需要通过流读取 Excel 内容）
        List<Course> list = ExcelImportUtil.importExcel(excelFile.getInputStream(), Course.class, params);
        courseService.save(list);

        return "redirect:/course/findAll";
    }


    //导出excel
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception{

        //查询课程数据
        List<Course> list = courseService.findAll();

        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("课程列表数据", "课程信息"),
                Course.class,list);

        //response.setHeader(...)：设置 HTTP 响应头，告诉浏览器如何处理返回的内容；
        /*Content-Disposition：核心响应头，作用是「指定内容的处理方式」：
        attachment：表示 “以附件形式下载”（浏览器会弹出下载框，而不是直接打开 Excel）；
        fileName=：指定下载文件的名称；*/
        response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode("课程信息列表.xls","UTF-8"));

        //response.getOutputStream()：获取响应的字节输出流 → 这个流直接对接前端浏览器，往流里写数据就是把数据传给浏览器；
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);

        outputStream.close();
        workbook.close();
    }
}
