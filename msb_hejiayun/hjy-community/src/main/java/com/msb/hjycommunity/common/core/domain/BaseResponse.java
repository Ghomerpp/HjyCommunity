package com.msb.hjycommunity.common.core.domain;


import java.io.Serializable;

/**
 * 响应结果封装对象
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/17 - 11 - 17 - 19:15
 * Description: com.msb.hjycommunity.common.core.domain
 */
//Serializable这是让这个类具备 “网络传输资格”。后端数据要通过网络发给前端，需要先 “序列化”（变成可传输的格式），这个接口就是通行证，确保传输过程中数据不损坏。
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1901152752394073986L;

    /*
    响应状态码
    */
    private String code;

    /*
    响应结果描述
    */
    private String message;

    /*
    返回的数据
    */
    /*<T> 是 Java 泛型
    * 不用为不同的返回数据类型（比如 User、String、List 等）写多个重复的响应类，同时保证编译时的类型安全。*/
    private T data;


    /*
    * 成功返回
    * */
    public static <T> BaseResponse<T> success(T data){
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResultCode.SUCCESS.getCode());
        response.setMessage(ResultCode.SUCCESS.getMessage());
        response.setDate(data);

        return response;
    }
    /*
    * 失败返回
    * */
    public static <T> BaseResponse<T> fail(String message){
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResultCode.ERROR.getCode());
        response.setMessage(message);
        return response;
    }

    public static <T> BaseResponse<T> fail(String code, String message){

        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(message);

        return response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return data;
    }

    public void setDate(T date) {
        this.data = date;
    }
}
