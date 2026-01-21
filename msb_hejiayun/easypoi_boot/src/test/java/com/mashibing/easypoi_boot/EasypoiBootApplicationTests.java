package com.mashibing.easypoi_boot;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.mashibing.easypoi_boot.pojo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.microsoft.schemas.vml.STTrueFalse.T;

@SpringBootTest
public class EasypoiBootApplicationTests {

    public List<User> getUser(){

        ArrayList<User> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("文渊");
            user.setAge(16+i);
            user.setBri(new Date());
            user.setStatus(String.valueOf(i % 2));
            user.setHobby(Arrays.asList("抽烟","喝酒","烫头"));
            user.setCard(new Card("21223199010113210","北京朝阳区群众"));
//            user.setOrderList(Arrays.asList(new Order("1001","超短裙"),new Order("1001","棒棒糖")));
            user.setPhoto("C:\\Users\\25734\\Pictures\\ma.jpg");

            list.add(user);
        }
        return list;
    }

    //导出到excel文件里面
    @Test
    public void testExportExcel() throws IOException {

        //入参 1:标题和工作表名称;入参 2：实体类(User)的字节码文件;入参 3：你自己定义的工具方法
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "测试"), User.class, getUser());

        //创建文件输出流，指定 Excel 保存路径
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\25734\\Desktop\\emp.xls");
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

    @Test
    public void testImportExcel() throws Exception{

        ImportParams params = new ImportParams();
        params.setTitleRows(1); //标题列占几行
        params.setHeadRows(1); //列名占几行
        params.setNeedSave(true);
        params.setSaveUrl("E:\\msb_hejiayun\\msb_hejiayun\\easypoi_boot\\src\\main\\resources\\static");

        List<Emp> list = ExcelImportUtil.importExcel(new FileInputStream("C:\\Users\\25734\\Desktop\\emp.xls"), Emp.class,params);

        list.forEach(System.out::println);

    }
    //===========================================多sheet页导入方法=================================

    //接收Excel文件导入的多个sheet页
    //public static <T> ：泛型声明，T 是「任意实体类」的占位符，这个写法让这个方法变成了万能通用方法，可以适配你的Emp.class/User.class/Card.class等任何实体类
    //List<T> ：方法返回值是「指定实体类的集合」，比如传Emp.class就返回List<Emp>，传User.class就返回List<User>
    public static <T> List<T> importMultiSheet(String filePath,  //Excel 文件的完整路径，比如你的 C:\\Users\\25734\\Desktop\\emp.xls
                                               int sheetIndex,  //要读取的 sheet 页下标
                                               Integer titleRows,  //Excel 的大标题行占几行，无大标题传0
                                               Integer headerRows,  //Excel 的表头列名行占几行
                                               Class<T> pojoClass){  //要转换的实体类字节码
        //创建 EasyPoi 的导入配置对象，所有导入规则都通过这个对象配置。
        ImportParams params = new ImportParams();

        //设置参数:
        params.setStartSheetIndex(sheetIndex);
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        //设置「Excel 表头必须包含的关键字段」，表示表头必须包含的字段，不包含就报错
        params.setImportFields(new String[]{"用户ID"});

        List<T> list = null;

        try {
            //调用 EasyPoi 的核心导入方法，传入「文件流 + 实体类 + 配置对象」，解析数据并赋值给集合；
            list = ExcelImportUtil.importExcel(new FileInputStream(filePath), pojoClass, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //测试导入多个sheet页
    @Test
    public void testImportMultiSheet(){
        String excelPath = "C:\\Users\\25734\\Desktop\\login.xls";

        List<LoginUser> loginUsers = importMultiSheet(excelPath, 0, 1, 1, LoginUser.class);
        loginUsers.forEach(System.out::println);

        List<LoginUser> loginUrls = importMultiSheet(excelPath, 1, 1, 1, LoginUser.class);
        loginUsers.forEach(System.out::println);
    }

    //===========================================多sheet页导出方法=================================

    /*objects[0] → 对应第一个参数：List<LoginUser> 登录用户 集合
    objects[1] → 对应第二个参数：List<LoginUrl> URL路径 集合*/
    public static void exportMultiSheet(Object... objects){  //可变参数列表,可以传零个一个或多个参数

        //创建LoginUser导出对象
        ExportParams loginUserParams = new ExportParams();

        //设置sheet名称
        loginUserParams.setSheetName("登录用户"); //【Sheet 工作表的名称】
        loginUserParams.setTitle("登录用户列表"); //【表格的大标题】

        //使用map集合 封装sheet，一个Sheet对应一个hashMap
        HashMap<String, Object> sheet1Map = new HashMap<>();

        //三个固定的key
        sheet1Map.put("title",loginUserParams);  //设置title
        sheet1Map.put("entity",LoginUser.class);  //实体类字节码
        sheet1Map.put("data",objects[0]);  //设置sheet页中的数据


        //=================================sheet2=====================================
        //创建LoginUser导出对象
        ExportParams loginUrlParams = new ExportParams();

        //设置sheet名称
        loginUrlParams.setSheetName("URL路径");
        loginUrlParams.setTitle("URL路径");

        //使用map集合 封装sheet
        HashMap<String, Object> sheet2Map = new HashMap<>();

        //设置title
        sheet2Map.put("title",loginUrlParams);
        sheet2Map.put("entity",LoginUrl.class);

        //设置sheet页中的数据
        sheet2Map.put("data",objects[1]);

        //将sheet1和sheet2 包装起来
        List<Map<String, Object>> sheetList = new ArrayList<>();
        sheetList.add(sheet1Map);
        sheetList.add(sheet2Map);

        //执行导出方法
        Workbook workbook = ExcelExportUtil.exportExcel(sheetList, ExcelType.HSSF);

        try {
            workbook.write(new FileOutputStream("C:\\Users\\25734\\Desktop\\loginExport.xls"));
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExportMultiSheet(){
        ArrayList<LoginUser> sheet1 = new ArrayList<>();
        sheet1.add(new LoginUser("1001","向阳","123456",new Date(),"0"));
        sheet1.add(new LoginUser("1002","于谦","123456",new Date(),"1"));
        sheet1.add(new LoginUser("1003","小岳岳","123456",new Date(),"0"));

        ArrayList<LoginUrl> sheet2 = new ArrayList<>();
        sheet2.add(new LoginUrl("1001","get","http://127.0.0.1:8080"));
        sheet2.add(new LoginUrl("1001","post","http://127.0.0.1:8080/login"));

        exportMultiSheet(sheet1,sheet2);
    }
}
