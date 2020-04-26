package com.xlx.admin.controller.system;

import com.xlx.framework.util.ShiroUtil;
import com.xlx.system.entity.User;
import com.xlx.system.service.MenuService;
import com.xlx.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
        Set<String> roles = roleService.listRolesByUserId(currentUser.getUserId());
        Set<String> permissions = menuService.listMenuPermission(currentUser.getUserId());
        modelMap.put("user",currentUser);
        modelMap.put("roles",roles);
        modelMap.put("permissions",permissions);
        return "index";
    }
}
