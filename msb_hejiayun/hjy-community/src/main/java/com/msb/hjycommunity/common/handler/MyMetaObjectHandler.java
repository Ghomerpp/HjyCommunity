package com.msb.hjycommunity.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义填充控制器
 * @author Licyh
 * @version: 1.0
 * @since 2025/12/29 - 12 - 29 - 20:43
 * Description: com.msb.hjycommunity.common.handler
 */

//@Component 是 Spring 框架的核心注解之一，你可以把它理解为：给 Java 类贴一个 “可被 Spring 管理” 的标签。
//拦截器从 Spring 容器获取你的「自定义填充处理器」
//你的 MyMetaObjectHandler 加了 @Component 注解，Spring 启动时已经将它实例化并存入容器；
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //insert时，要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {

        //根据属性名称要填充的字段
        this.strictInsertFill(metaObject,"createBy",String.class,"admin");
        this.strictInsertFill(metaObject,"updateBy",String.class,"admin");
        this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        this.strictInsertFill(metaObject,"updateTime", Date.class,new Date());

    }

    //在update操作的时候，要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateBy",String.class,"admin");
        this.strictUpdateFill(metaObject,"updateTime", Date.class,new Date());
    }
}
