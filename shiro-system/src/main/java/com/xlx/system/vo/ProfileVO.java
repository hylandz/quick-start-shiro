package com.xlx.system.vo;

import com.xlx.system.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * profile
 *
 * @author xielx at 2020/4/28 9:42
 */
@Data
public class ProfileVO {
    
    private Long userId;
    private String avatarUrl;
    private String avatarName;
    private String userName;
    private String userReal;
    private Integer gender;
    private String email;
    private String phone;
    private String loginIp;
    private LocalDateTime loginDate;
    protected LocalDateTime gmtCreate;
    private String roleNames;
    private String deptName;
    
}
