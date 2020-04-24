package com.xlx.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xielx at 2020/4/22 13:33
 */
public abstract class BaseEntity<T> implements Serializable {
    
    /**
     * 状态
     * 0: 正常
     * 1: 禁用
     * 2: 删除
     */
    @Getter
    @Setter
    protected Integer status;
    /**
     * 创建时间
     */
    @Getter
    @Setter
    protected LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    @Getter
    @Setter
    protected LocalDateTime gmtModified;
    
    /**
     * ?
     * @return id
     */
    protected abstract Serializable pkVal();
}
