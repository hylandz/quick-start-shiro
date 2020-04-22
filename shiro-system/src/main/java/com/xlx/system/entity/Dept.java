package com.xlx.system.entity;

import com.xlx.system.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_dept")
public class Dept extends BaseEntity<Dept> {

    private static final long serialVersionUID=1L;

    /**
     * 主键,id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父级id
     */
    private Long parentId;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

}
