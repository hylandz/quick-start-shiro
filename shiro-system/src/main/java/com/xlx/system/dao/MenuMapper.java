package com.xlx.system.dao;

import com.xlx.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface MenuMapper extends BaseMapper<Menu> {

    
    List<Menu> selectMenuByUserId(Long userId);
}
