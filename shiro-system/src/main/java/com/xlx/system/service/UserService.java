package com.xlx.system.service;

import com.xlx.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xlx.system.vo.ProfileVO;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface UserService extends IService<User> {

    ProfileVO findProfile(@NotNull User user);
}
