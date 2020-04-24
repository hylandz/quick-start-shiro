package com.xlx.common.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author xielx at 2020/4/24 22:51
 */
public enum UserStatusEnum {
    
    OK(0,"正常"),
    DELETED(1, "用户已删除"),
    DISABLE(2, "用户已停用")
    ;
    
    
    @Getter
    private final Integer code;
    @Getter
    private final String info;
    
    UserStatusEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }
}
