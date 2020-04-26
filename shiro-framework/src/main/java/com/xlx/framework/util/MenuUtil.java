package com.xlx.framework.util;

import com.xlx.system.entity.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 菜单工具类
 *
 * @author xielx at 2020/4/26 23:25
 */
public class MenuUtil {
    
    public static List<Menu> sortedMenus(List<Menu> menuList,int parentId){
        List<Menu> parentMenus = new ArrayList<>();
        for (Iterator<Menu> it = menuList.iterator(); it.hasNext(); ) {
            while (it.hasNext()){
                Menu menu = it.next();
                if (menu.getParentId() == parentId){
                    // parentId=0为父菜单
                    findParentMenu(menuList,menu);
                    parentMenus.add(menu);
                }
            }
        }
    
        return parentMenus;
    }
    
    private static void findParentMenu(List<Menu> menuList, Menu parent) {
        //
        List<Menu> childList = getChildList(menuList,parent);
        parent.setChildren(childList);
        // 遍历孙子
        for (Menu son : childList){
            if (hasChild(menuList,son)){
                for (Menu menu : childList) {
                    // 递归
                    findParentMenu(menuList, menu);
                }
            }
        }
    }
    
    private static boolean hasChild(List<Menu> menuList, Menu son) {
        return getChildList(menuList, son).size() > 0;
    }
    
    /**
     * 获取孩子集合
     * @param menuList 集合
     * @param parentMenu 父对象
     * @return 子集合
     */
    private static List<Menu> getChildList(List<Menu> menuList, Menu parentMenu) {
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menuList) {
            // 菜单的parentId是父菜单的id
            if (Objects.equals(menu.getParentId(), parentMenu.getMenuId())) {
                list.add(menu);
            }
        }
        return list;
    }
}
