package com.spring.splendor.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * role_auth
 * @author
 */
@Data
public class RoleAuth implements Serializable {
    private Integer rAId;

    private Integer roleId;

    private Integer authId;

    private static final long serialVersionUID = 1L;
}
