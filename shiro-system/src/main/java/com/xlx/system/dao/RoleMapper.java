package com.xlx.system.dao;

import com.xlx.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface RoleMapper extends BaseMapper<Role> {
    
    
    Set<String> selectRolesByUserId(@Param("userId") Long userId);
    Set<String> selectRolesByUserName(@Param("userName") String userName);
}
