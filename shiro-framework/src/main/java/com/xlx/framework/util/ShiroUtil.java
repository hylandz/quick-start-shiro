package com.xlx.framework.util;

import com.xlx.common.util.CryptUtil;
import com.xlx.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

/**
 * shiro工具类
 *
 * @author xielx at 2020/4/22 21:04
 */
public class ShiroUtil {
    
    private static final String algorithmName = "md5";
    private static final Integer hashIterations = 2;
    
    /**
     * 生成32位的随机数
     * @return String
     */
    public static String generateHexRandomNumber(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }
    
    /**
     * 加密
     * @param password 页面表单输入的密码
     * @param credentialsSalt 组合盐(username + salt)
     * @return 十六进制的字符串
     */
    public static String encryptPassword(String password,String credentialsSalt){
        return new SimpleHash(algorithmName,password, ByteSource.Util.bytes(credentialsSalt),hashIterations).toHex();
    }
    
    /**
     * 获取Subject对象
     * @return 当前Subject对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
    
    
    /**
     * 获取当前登录用户
     * @return 当前登录用户
     */
    public static User getCurrentUser(){
        return (User) getSubject().getPrincipal();
    }
    
    
    public static void main(String[] args) {
        // String encode = CryptUtil.base64Encode("learn shiro quickly and systematically");
        String password = "admin";
        String salt = generateHexRandomNumber();
        System.out.println("salt=" + salt);
        System.out.println("encryptPassword=" + encryptPassword(password,"admin" + salt));
    }
}
