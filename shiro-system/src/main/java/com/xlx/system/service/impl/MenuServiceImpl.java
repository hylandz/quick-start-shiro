package com.xlx.system.service.impl;

import com.xlx.system.entity.Menu;
import com.xlx.system.dao.MenuMapper;
import com.xlx.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<Menu> listMenuByUserId(@NotNull Long id) {
        return menuMapper.selectMenuByUserId(id);
    }
}
