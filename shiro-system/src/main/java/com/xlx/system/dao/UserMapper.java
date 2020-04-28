package com.xlx.system.dao;

import com.xlx.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlx.system.vo.ProfileVO;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface UserMapper extends BaseMapper<User> {
    
    ProfileVO selectProfileByUserId(Long userId);
    
}
