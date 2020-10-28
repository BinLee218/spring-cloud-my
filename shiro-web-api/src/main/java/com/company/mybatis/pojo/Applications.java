package com.company.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * applications
 * @author
 */
@Data
public class Applications implements Serializable {
    /**
     * 系统ID
     */
    private Integer appId;

    /**
     * 系统名字
     */
    private String appName;

    /**
     * 系统描述
     */
    private String appDesc;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
