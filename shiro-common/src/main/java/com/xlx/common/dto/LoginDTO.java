package com.xlx.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * login
 *
 * @author xielx at 2020/4/25 20:34
 */
@Data
public class LoginDTO {
    
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    private Boolean rememberMe;
    
    @NotNull
    private String verifyCode;
}
