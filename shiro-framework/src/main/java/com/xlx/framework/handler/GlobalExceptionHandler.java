package com.xlx.framework.handler;

import com.xlx.common.dto.Result;
import com.xlx.common.enums.ResultCodeEnum;
import com.xlx.common.exception.UserException;
import com.xlx.common.util.ExceptionLogUtil;
import com.xlx.framework.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常捕获
 *
 * @author xielx at 2020/4/24 21:50
 */
@RestControllerAdvice(basePackages = "com.xlx")
@Slf4j
public class GlobalExceptionHandler {
    
    
    /**
     * 处理Controller层出现的方法参数校验异常
     * @param ma MethodArgumentNotValidException
     * @return Result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ma){
        BindingResult bindingResult = ma.getBindingResult();
        Map<String,String> map = null;
        if (bindingResult.hasErrors()){
           map = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
           log.error("MethodArgumentNotValidException:{}",map);
        }
        return Result.error().message("参数异常").data(map);
    }
    
    /**
     * 处理Service层出现的校验异常
     * @param con ConstraintViolationException
     * @return Result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(ConstraintViolationException con){
        Set<ConstraintViolation<?>> violations = con.getConstraintViolations();
        Map<Path,String> map = null;
        if (violations.size() > 0){
            map = violations.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
            log.error("ConstraintViolationException:{}",map);
        }
        return Result.error().message("参数异常").data(map);
    }
    
    
    
    /*连续多次输入错误*/
    @ExceptionHandler(ExcessiveAttemptsException.class)
    public Result<Object> handleExcessiveAttemptsException(ExcessiveAttemptsException ex){
        log.error("ExcessiveAttemptsException:{}" ,ex.getMessage());
        return Result.error(ResultCodeEnum.LOGIN_LOCKED);
    }
    
    /*用户名密码错误*/
    @ExceptionHandler(CredentialsException.class)
    public Result<Object> handleCredentialsException(CredentialsException cre){
        log.error("CredentialsException:{}" ,cre.getMessage());
        return Result.error(ResultCodeEnum.CREDENTIALS_ERROR);
    }
    
    /*用户异常*/
    @ExceptionHandler(UserException.class)
    public Result<Object> handleUserException(UserException u){
        log.error("UserException:{}" ,u.getMessage());
        return Result.error(ResultCodeEnum.ACCOUNT_NOT_EXISTS);
    }
    
    /*账号不存在*/
    @ExceptionHandler(UnknownAccountException.class)
    public Result<ResultCodeEnum> handleUnknownAccountException(UnknownAccountException u){
        log.error("UnknownAccountException:{}",u.getMessage());
        return Result.error(ResultCodeEnum.ACCOUNT_NOT_EXISTS);
    }
    
    /*账号被锁/停用*/
    @ExceptionHandler(LockedAccountException.class)
    public Result<ResultCodeEnum> handleLockedAccountException(LockedAccountException lock){
        log.error("LockedAccountException:{}",lock.getMessage());
        return Result.error(ResultCodeEnum.ACCOUNT_DISABLED);
    }
    
    /*未授权*/
    @ExceptionHandler(UnauthorizedException.class)
    public Result<ResultCodeEnum> handleUnauthorizedException(UnauthorizedException u){
        log.error("UnauthorizedException:{}",u.getMessage());
        return Result.error(ResultCodeEnum.UNAUTHORIZED_ERROR);
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
        String message = ExceptionLogUtil.getMessage(e);
        log.error("Exception:{}",message);
        return Result.error(ResultCodeEnum.UNKNOWN_ERROR);
    }
}
