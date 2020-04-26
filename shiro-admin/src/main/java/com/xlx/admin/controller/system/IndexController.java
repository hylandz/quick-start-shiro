package com.xlx.admin.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlx.framework.util.MenuUtil;
import com.xlx.framework.util.ShiroUtil;
import com.xlx.system.entity.Menu;
import com.xlx.system.entity.User;
import com.xlx.system.service.MenuService;
import com.xlx.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * index
 *
 * @author xielx at 2020/4/22 15:39
 */
@Controller
@Slf4j
public class IndexController {
    
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    
    @GetMapping("/index")
    public String index(ModelMap modelMap){
        User currentUser = ShiroUtil.getCurrentUser();
        log.info("currentUser:{}",currentUser);
        List<Menu> menus;
        if (currentUser.isAdmin()){
           menus = menuService.list(new QueryWrapper<Menu>().eq("`status`","1"));
        }else {
            menus = menuService.listMenuByUserId(currentUser.getUserId());
        }
        modelMap.put("user",currentUser);
        modelMap.put("menus", MenuUtil.sortedMenus(menus,0));
        modelMap.put("roles",roles);
        return "index";
    }
}
