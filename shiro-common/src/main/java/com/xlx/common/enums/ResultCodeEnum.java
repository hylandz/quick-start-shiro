package com.xlx.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Result枚举
 *
 * @author xielx at 2020/4/24 21:31
 */
public enum ResultCodeEnum {
    
    SUCCESS(true, 10000,"成功"),
    FAILED(false,20001,"失败"),
    UNKNOWN_ERROR(false,20002,"系统异常"),
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
