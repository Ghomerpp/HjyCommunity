package com.msb.hjycommunity.web.test;

import com.msb.hjycommunity.common.core.domain.BaseResponse;
import com.msb.hjycommunity.common.core.exception.BaseException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/17 - 11 - 17 - 20:20
 * Description: com.msb.hjycommunity.web.test
 */
@RestController
//@RestController：组合注解 = @Controller + @ResponseBody，表示该类是控制器，且所有方法的返回值都会直接序列化为 JSON/XML 响应（无需额外加 @ResponseBody）。
@RequestMapping("/user")
public class UserController {
    //1. 根据用户 ID 查询用户接口
    @RequestMapping("/queryUserById")
    public BaseResponse<User> queryUserById(String userId){
        if(userId != null){
            return BaseResponse.success(new User(userId,"spike"));
        }else {
            return BaseResponse.fail("查询用户信息失败！");
        }
    }

    //2. 新增用户接口（含参数校验）
    @RequestMapping("/addUser")
    //@Validated 负责 “检查前端传的 User 参数合不合规，如 @NotNull(message = "用户名不能为空")
    //而 bindingResult 就是专门用来存放这些检查结果（错误信息） 的容器。如果没有这个参数，Spring 会直接抛出校验异常（而非把错误存到这里）；
    public BaseResponse addUser(@Validated User user, BindingResult bindingResult){
        // 提取所有参数校验错误
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        //如果参数校验失败，会将错误信息封装成对象组装到BindingResult
        if(!fieldErrors.isEmpty()){
            return BaseResponse.fail(fieldErrors.get(0).getDefaultMessage());
        }
        return BaseResponse.success("OK...");
    }

    @RequestMapping("/queryUser")
    public BaseResponse queryUser(User user){
        throw new BaseException("500","测试异常类!!!");
    }
}
