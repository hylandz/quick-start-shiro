package com.xlx.framework.exception;

/**
 * 文件操作异常
 *
 * @author xielx at 2020/4/28 17:01
 */
public class FileException extends RuntimeException {
    
    public FileException(){
        super();
    }
    
    public FileException(String message){
        super(message);
    }
}
