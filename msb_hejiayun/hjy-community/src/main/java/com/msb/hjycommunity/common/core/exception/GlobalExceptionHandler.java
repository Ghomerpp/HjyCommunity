package com.msb.hjycommunity.common.core.exception;

import com.msb.hjycommunity.common.core.domain.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *全局异常处理器
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/18 - 11 - 18 - 9:13
 * Description: com.msb.hjycommunity.common.core.exception
 */
//本质：组合注解 = @ControllerAdvice（全局拦截 Controller 层的异常 / 数据绑定等） + @ResponseBody（将方法返回值序列化为 JSON 响应）；
@RestControllerAdvice
public class GlobalExceptionHandler {
    //Spring 会自动扫描所有标注了 @RestControllerAdvice/@ControllerAdvice 的类，当项目中任何 @RestController/@Controller 层抛出 BaseException 异常时，会被这个注解 “匹配到”，并交由当前方法处理
    //范围限定：只处理 BaseException（包括它的子类，如果有的话）
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse baseExceptionHandler(BaseException e){
        return BaseResponse.fail(e.getDefaultMessage());
    }
}
