package com.company.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * auth
 * @author 
 */
@Data
public class Auth implements Serializable {
    /**
     * 主键
     */
    private Integer authId;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限值
     */
    private String authValue;

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

    private String path;

    private Integer root;

    private static final long serialVersionUID = 1L;
}