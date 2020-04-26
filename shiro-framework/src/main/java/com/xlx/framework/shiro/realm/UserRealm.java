package com.xlx.framework.shiro.realm;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlx.common.enums.UserStatusEnum;
import com.xlx.framework.exception.UserNotExistException;
import com.xlx.framework.exception.UserStatusException;
import com.xlx.system.entity.Menu;
import com.xlx.system.entity.User;
import com.xlx.system.service.MenuService;
import com.xlx.system.service.RoleService;
import com.xlx.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义Reaml
 *
 * @author xielx at 2020/4/22 21:03
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MenuService menuService;
    
    /**
     * 授权
     * @param principals 凭证
     * @return  AuthorizationInfo 授权异常
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("PrincipalCollection principals={}", JSON.toJSONString(principals));
       
        Object principal = principals.getPrimaryPrincipal();
        log.info("Object principals.getPrimaryPrincipal() = {}",JSON.toJSONString(principal));
    
        User user = (User) principal;
        log.info("User user = {}",JSON.toJSONString(user));
    
        log.info("-------SecurityUtils.getSubject():");
        Object object = SecurityUtils.getSubject().getPrincipal();
        User user1 = (User) object;
        log.info("object == principal :,{}",(object == principal));
        log.info("User user1 = {}",JSON.toJSONString(user1));
        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        // 角色集
        Set<String> roleSet = roleService.listRolesByUserId(user1.getUserId());
        authorizationInfo.setRoles(roleSet);
        
        // 权限集
        List<Menu> menuList = menuService.listMenuByUserId(user1.getUserId());
        Set<String> permSet = menuList.stream().map(Menu::getPermis).collect(Collectors.toSet());
        authorizationInfo.setStringPermissions(permSet);
        
        return authorizationInfo;
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
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", username));
    
        if (user == null){
            //UnknownAccountException
            throw new UnknownAccountException("用户不存在");
        }
        
        // 用户被禁用/锁定
        if (UserStatusEnum.DISABLE.getCode().equals(user.getStatus())){
            throw new LockedAccountException(UserStatusEnum.DISABLE.getInfo());
        }
        // 用户被删除
        if (UserStatusEnum.DELETED.getCode().equals(user.getDelFlag())){
            throw new UserStatusException(UserStatusEnum.DELETED.getInfo());
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
