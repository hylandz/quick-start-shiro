package com.xlx.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
@TableName("sys_user")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 昵称
     */
    private String avatarName;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 帐号
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String userReal;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * cookie使用
     */
    private String token;

    /**
     * 性别,1:男;0:女
     */
    private Integer gender;

    /**
     * 出生年月,yyyy-MM-dd
     */
    private LocalDate birth;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 逻辑删除,0:删除;1:未删除
     */
    private Integer delFlag;

    /**
     * 最后登录ip
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
    
    
    /**
     * 获取组合盐
     * @return salt = username + salt
     */
    public String getCredentials(){
        return this.userName + this.salt;
    }
    
    public boolean isAdmin(){
        return isAdmin(userId);
    }
    public static boolean isAdmin(Long userId){
        return Objects.equals(1L,userId);
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
