package com.company.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * role_auth
 * @author
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuth implements Serializable {
    private Integer rAId;

    private Integer roleId;

    private String authValue;

    private static final long serialVersionUID = 1L;
}
