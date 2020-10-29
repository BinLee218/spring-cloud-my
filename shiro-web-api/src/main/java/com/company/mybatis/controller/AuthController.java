package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.controller.request.auth.AuthRequest;
import com.company.mybatis.controller.request.auth.AuthSaveRequest;
import com.company.mybatis.controller.request.auth.AuthUpdateRequest;
import com.company.mybatis.controller.response.AuthRoleResponse;
import com.company.mybatis.dto.AuthPage;
import com.company.mybatis.dto.result.RolePermission;
import com.company.mybatis.facade.HomeFacadeService;
import com.company.mybatis.pojo.Applications;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.service.AuthService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @Autowired
    private AuthService authService;

    @GetMapping(value = "/auth/without/role")
    public ResponseEntity<ApiResponse<AuthRoleResponse>> authWithoutRole() {
        List<RolePermission> auths = homeFacadeService.getAuthWithoutRole();
        return super.executeApiResponseResponseEntity(AuthRoleResponse.builder().auths(auths).build());
    }

    @PostMapping(value = "/auth/all")
    @RequiresRoles(value = {"admin", "operator"}, logical = Logical.OR)
    public ResponseEntity<ApiResponse<PageInfo<Auth>>> getAllRoles(@RequestBody AuthRequest authRequest) throws Exception {
        AuthPage authPage = new AuthPage();
        BeanUtils.copyProperties(authPage, authRequest);
        PageInfo<Auth> pageInfo = authService.getAll(authPage);
        return super.executeApiResponseResponseEntity(pageInfo);
    }

    @PostMapping(value = "/auth/add")
    @RequiresRoles(value = {"admin", "operator"}, logical = Logical.OR)
    public ResponseEntity<ApiResponse<Void>> addRole(@RequestBody AuthSaveRequest authSaveRequest) {
        authService.addAuth(authSaveRequest);
        return super.executeApiResponseResponseEntity(null);
    }

    @PostMapping(value = "/auth/update")
    @RequiresRoles(value = {"admin", "operator"}, logical = Logical.OR)
    public ResponseEntity<ApiResponse<Void>> updateRole(@RequestBody AuthUpdateRequest authUpdateRequest) {
        authService.updateAuth(authUpdateRequest);
        return super.executeApiResponseResponseEntity(null);
    }

    @GetMapping(value = "/auth/app")
    @RequiresRoles(value = {"admin", "operator"}, logical = Logical.OR)
    public ResponseEntity<ApiResponse<List<Applications>>> getAllApp() {
        List<Applications> appList = authService.getAllApp();
        return super.executeApiResponseResponseEntity(appList);
    }

}
