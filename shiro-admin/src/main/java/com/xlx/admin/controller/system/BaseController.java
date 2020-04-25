package com.xlx.admin.controller.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author xielx at 2020/4/25 20:40
 */
public class BaseController {
    
    
    protected Subject getSubject(){
        return SecurityUtils.getSubject();
    }
}
