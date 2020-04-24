package com.xlx.framework.handler;

import com.xlx.common.dto.Result;
import com.xlx.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author xielx at 2020/4/24 21:50
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    
    
    
    
    
    /**
     * 处理未知异常
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result<ResultCodeEnum> handleException(Exception e){
        return Result.error(ResultCodeEnum.UNKNOWN_ERROR);
    }
}
