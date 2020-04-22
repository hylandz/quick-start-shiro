package com.xlx.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xielx at 2020/4/22 13:33
 */
public abstract class BaseEntity<T> implements Serializable {
    
    /**
     * 状态
     */
    protected Integer status;
    /**
     * 创建时间
     */
    protected LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    protected LocalDateTime gmtModified;
    
    /**
     * ?
     * @return id
     */
    protected abstract Serializable pkVal();
}
