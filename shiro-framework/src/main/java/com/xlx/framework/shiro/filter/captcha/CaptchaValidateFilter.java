package com.xlx.framework.shiro.filter.captcha;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 验证码校验过滤器
 *
 * @author xielx at 2020/4/24 16:33
 */
public class CaptchaValidateFilter extends AccessControlFilter {
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
}
