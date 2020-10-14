package com.company.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色值
     */
    private String roleValue;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}