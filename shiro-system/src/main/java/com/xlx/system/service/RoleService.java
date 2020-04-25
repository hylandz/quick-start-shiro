package com.xlx.system.service;

import com.xlx.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface RoleService extends IService<Role> {

    
    
    Set<String> listRolesByUserId(Long userId);
    Set<String> listRolesByUserName(String userName);
}
