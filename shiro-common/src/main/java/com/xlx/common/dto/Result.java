package com.xlx.common.dto;

import com.xlx.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * ResponseResult
 *
 * @author xielx at 2020/4/24 21:24
 */
@Data
public class Result <T> {
    
    /**
     *
     */
    private Boolean success;

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String message;
    
    /**
     *
     */
    private T data;
    
    /*私有构造*/
    private Result(){}
    
    
    // 通用成功
    public static <T> Result<T> ok(){
        Result<T> r = new Result<>();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }
    // 通用失败
    public static <T> Result<T> error(){
        Result<T> r = new Result<>();
        r.setSuccess(ResultCodeEnum.FAILED.getSuccess());
        r.setCode(ResultCodeEnum.FAILED.getCode());
        r.setMessage(ResultCodeEnum.FAILED.getMessage());
        return r;
    }
    // 设置结果(返回状态,状态码,状态码描述)
    public static <T> Result<T> error(ResultCodeEnum codeEnum){
        Result<T> r = new Result<>();
        r.setSuccess(codeEnum.getSuccess());
        r.setCode(codeEnum.getCode());
        r.setMessage(codeEnum.getMessage());
        return r;
    }
    
    /**-------------------------- 链式编程,返回类本身--------------------------**/
    
    public Result<T> success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> data(T data){
        this.setData(data);
        return this;
    }
    
    
}
