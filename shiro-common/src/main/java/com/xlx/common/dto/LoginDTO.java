package com.xlx.common.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * login
 *
 * @author xielx at 2020/4/25 20:34
 */
@Data
@ToString
public class LoginDTO {
    
    @NotNull(message = "用户名不能为空")
    private String username;
    
    @NotNull(message = "密码不能为空")
    private String password;
    
    private Boolean rememberMe = Boolean.FALSE;
    
    @NotNull(message = "验证码不能为空")
    private String validateCode = "ccc";
}
