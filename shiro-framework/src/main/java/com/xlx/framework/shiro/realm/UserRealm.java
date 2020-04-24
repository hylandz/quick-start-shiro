package com.xlx.framework.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlx.common.enums.UserStatusEnum;
import com.xlx.framework.exception.UserNotExistException;
import com.xlx.framework.exception.UserStatusExistException;
import com.xlx.system.entity.User;
import com.xlx.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Reaml
 *
 * @author xielx at 2020/4/22 21:03
 */
public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;
    
    /**
     * 授权
     * @param principals 凭证
     * @return  AuthorizationInfo 授权异常
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        
        
        
        return null;
    }
    
    
    /**
     * 身份认证
     * @param token 登录token
     * @return AuthenticationInfo
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", upToken));
    
        if (user == null){
            throw new UserNotExistException("用户不存在");
        }
        
        // 用户被禁用/锁定
        if (UserStatusEnum.DISABLE.getCode().equals(user.getStatus())){
            throw new UserStatusExistException(UserStatusEnum.DISABLE.getInfo());
        }
        // 用户被删除
        if (UserStatusEnum.DELETED.getCode().equals(user.getDelFlag())){
            throw new UserStatusExistException(UserStatusEnum.DELETED.getInfo());
        }
    
    
        return new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getCredentials()),getName());
    }
    
    /**
     * 清除缓存权限
     */
    protected void clearCachedAuthorizationInfo() {
        super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
