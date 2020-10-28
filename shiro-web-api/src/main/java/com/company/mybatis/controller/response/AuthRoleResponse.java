package com.company.mybatis.controller.response;

import com.company.mybatis.dto.result.RolePermission;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/28
 */
@Getter
@Setter
@Builder
public class AuthRoleResponse  {

    List<RolePermission> auths;

}
