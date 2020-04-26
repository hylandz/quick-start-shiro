package com.xlx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlx.system.entity.Role;
import com.xlx.system.dao.RoleMapper;
import com.xlx.system.entity.UserRole;
import com.xlx.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlx.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public Set<String> listRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }
    
    @Override
    public Set<String> listRolesByUserName(String userName) {
        return roleMapper.selectRolesByUserName(userName);
    }
}
