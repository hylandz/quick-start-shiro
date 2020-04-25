package com.xlx.framework.handler;

import com.xlx.common.dto.Result;
import com.xlx.common.enums.ResultCodeEnum;
import com.xlx.framework.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author xielx at 2020/4/24 21:50
 */
@RestControllerAdvice(basePackages = "com.xlx")
@Slf4j
public class GlobalExceptionHandler {
    
    
    
    /*连续多次输入错误*/
    @ExceptionHandler(ExcessiveAttemptsException.class)
    public Result<Object> handleExcessiveAttemptsException(ExcessiveAttemptsException ex){
        log.error("ExcessiveAttemptsException:{}" ,ex.getMessage());
        return Result.error(ResultCodeEnum.LOGIN_LOCKED);
    }
    
    /*账号不存在*/
    @ExceptionHandler(UserNotExistException.class)
    public Result<ResultCodeEnum> handleUserNotExistException(UserNotExistException u){
        log.error("UserNotExistException:{}",u.getMessage());
        return Result.error(ResultCodeEnum.ACCOUNT_NOT_EXISTS);
    }
    
    /*账号被锁/停用*/
    @ExceptionHandler(LockedAccountException.class)
    public Result<ResultCodeEnum> handleLockedAccountException(LockedAccountException lock){
        log.error("LockedAccountException:{}",lock.getMessage());
        return Result.error(ResultCodeEnum.ACCOUNT_LOCKED);
    }
    
    /*其它Shiro身份认证异常*/
    @ExceptionHandler(AuthenticationException.class)
    public Result<ResultCodeEnum> handleAuthenticationException(AuthenticationException auth){
        log.error("AuthenticationException:{}" ,auth.getMessage());
        return Result.error(ResultCodeEnum.AUTHENTICATED_ERROR);
    }
    
    
    /*处理未知异常*/
    @ExceptionHandler(Exception.class)
    public Result<ResultCodeEnum> handleException(Exception e){
        log.error("Exception:{}",e.getMessage());
        return Result.error(ResultCodeEnum.UNKNOWN_ERROR);
    }
}
