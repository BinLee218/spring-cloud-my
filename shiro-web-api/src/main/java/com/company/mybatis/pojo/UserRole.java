package com.company.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * user_role
 * @author 
 */
@Data
public class UserRole implements Serializable {
    private Integer uRId;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}