package com.xlx.framework.exception;

import com.xlx.common.exception.UserException;

/**
 * 用户不存在异常
 *
 * @author xielx at 2020/4/24 22:07
 */
public class UserNotExistException extends UserException {
    
    
    public UserNotExistException(String message){
        super(message);
    }


}
