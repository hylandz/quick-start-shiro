package com.xlx.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlx.system.dao.UserMapper;
import com.xlx.system.entity.User;
import com.xlx.system.service.UserService;
import com.xlx.system.vo.ProfileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Override
    public ProfileVO findProfile(@NotNull User user) {
        return this.baseMapper.selectProfileByUserId(user.getUserId());
    }
}
