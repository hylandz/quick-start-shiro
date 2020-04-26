package com.xlx.system.service;

import com.xlx.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
public interface MenuService extends IService<Menu> {

    
    List<Menu> listMenuByUserId(@NotNull Long id);
    
    Set<String> listMenuPermission(@NotNull Long id);
}
