package com.xlx.common.exception;

/**
 * 用户异常
 *
 * @author xielx at 2020/4/24 22:11
 */
public class UserException extends RuntimeException {


    public UserException(){
        super();
    }
    
    
    public UserException(String message){
        super(message);
    }
    
}
