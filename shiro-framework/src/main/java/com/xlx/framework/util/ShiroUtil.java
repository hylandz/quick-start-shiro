package com.xlx.framework.util;

import org.apache.shiro.codec.Base64;

/**
 * shiro工具类
 *
 * @author xielx at 2020/4/22 21:04
 */
public class ShiroUtil {
    
    
    
    public static String base64Encode(String src){
        return Base64.encodeToString(src.getBytes());
    }
    
    public static String base64Decode(String encodeString){
        return Base64.decodeToString(encodeString);
    }
    
    public static void main(String[] args) {
        String code = "learn shiro quickly and systematically";
        String encode = base64Encode(code);
        System.out.println(encode);
        System.out.println(base64Decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        System.out.println(base64Decode(encode));
    }
}
