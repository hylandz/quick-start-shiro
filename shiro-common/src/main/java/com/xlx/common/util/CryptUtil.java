package com.xlx.common.util;


import java.util.Base64;

/**
 * 加密工具类
 *
 * @author xielx at 2020/4/25 21:58
 */
public class CryptUtil {
    
    public static String base64Encode(String src){
        return Base64.getEncoder().encodeToString(src.getBytes());
    }
    
    public static String base64Decode(String encodeString){
        return new String(Base64.getDecoder().decode(encodeString));
    }
}
