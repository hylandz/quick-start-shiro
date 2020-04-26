package com.xlx.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlx
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends BaseEntity<Menu> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 资源名称
     */
    private String menuName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单类型,菜单:1;按钮:0
     */
    private Integer type;

    /**
     * 资源url
     */
    private String url;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 资源权限
     */
    private String permis;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();
    

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
