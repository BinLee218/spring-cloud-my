package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.commons.jwt.JwtTokenUtils;
import com.company.mybatis.commons.jwt.TokenParam;
import com.company.mybatis.controller.request.AuthRoleRequest;
import com.company.mybatis.controller.request.LoginRequest;
import com.company.mybatis.controller.response.AuthRoleResponse;
import com.company.mybatis.controller.response.LoginResponse;
import com.company.mybatis.dto.result.RolePermission;
import com.company.mybatis.facade.HomeFacadeService;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.RoleAuth;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class AuthController extends BasicController {

    @Autowired
    private HomeFacadeService homeFacadeService;

    @PostMapping(value = "/auth/byRole")
    public ResponseEntity<ApiResponse<AuthRoleResponse>> authByRole(@RequestBody @Validated AuthRoleRequest authRoleRequest) {
        Integer roleId = authRoleRequest.getRoleId();
        List<RolePermission> auths = homeFacadeService.getAuthByRoleId(roleId);
        return super.executeApiResponseResponseEntity(AuthRoleResponse.builder().auths(auths).build());
    }

}
