package com.company.sharding.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * book_user
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUser implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String userLoginName;

    /**
     * 登录密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 盐
     */
    private String salt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否是系统用户 0否 1是
     */
    private Integer systemUser;

    private static final long serialVersionUID = 1L;
}