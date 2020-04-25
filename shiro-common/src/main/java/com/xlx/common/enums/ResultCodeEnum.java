package com.xlx.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Result枚举
 *
 * @author xielx at 2020/4/24 21:31
 */
public enum ResultCodeEnum {
    
    SUCCESS(true, 200,"成功"),
    FAILED(false,20001,"失败"),
    UNKNOWN_ERROR(false,20002,"系统异常"),
    LOGIN_LOCKED(false,20003,"你已连续输错5次,请10分钟后再试"),
    AUTHENTICATED_ERROR(false,20004,"用户认证失败"),
    CREDENTIALS_ERROR(false,20005,"用户名或密码错误"),
    ACCOUNT_NOT_EXISTS(false,20006,"账号不存在"),
    ACCOUNT_LOCKED(false,20007,"账号被锁定,请联系管理员"),
    ;
    
    @Getter
    private Boolean success;
    @Getter
    private Integer code;
    @Getter
    private String message;
    
    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
