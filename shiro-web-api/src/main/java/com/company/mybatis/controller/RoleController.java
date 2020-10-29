package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.controller.request.RoleRequest;
import com.company.mybatis.controller.request.RoleSaveRequest;
import com.company.mybatis.controller.request.RoleUpdateRequest;
import com.company.mybatis.controller.response.AuthRoleResponse;
import com.company.mybatis.dto.RolePage;
import com.company.mybatis.dto.result.RolePermission;
import com.company.mybatis.facade.HomeFacadeService;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.service.RoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author bin.li
 * @date 2020/10/21
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RoleController extends BasicController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private HomeFacadeService homeFacadeService;

    @PostMapping(value = "/roles")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<PageInfo<Role>>> getAllRoles(@RequestBody RoleRequest roleRequest) throws Exception {
        RolePage rolePage = new RolePage();
        BeanUtils.copyProperties(rolePage, roleRequest);
        log.info(rolePage.getStartTime());
        log.info(rolePage.getEndTime());
        PageInfo<Role> pageInfo = roleService.getAll(rolePage);
        return super.executeApiResponseResponseEntity(pageInfo);
    }

    @PostMapping(value = "/role/add")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<Void>> addRole(@RequestBody RoleSaveRequest roleSaveRequest) {
        roleService.addRole(roleSaveRequest);
        return super.executeApiResponseResponseEntity(null);
    }

    @PostMapping(value = "/role/update")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<Void>> updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        roleService.updateRole(roleUpdateRequest);
        return super.executeApiResponseResponseEntity(null);
    }

    @GetMapping(value = "/role/name/value")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<List<Role>>> getAllNameValue() {
        List<Role> roleList = roleService.getAllRole();
        return super.executeApiResponseResponseEntity(roleList);
    }

    @PostMapping(value = "/role/permissions")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<List<String>>> rolePermissions(@RequestBody Map<String, Integer> roleId) {
        List<String> rolePermissions = homeFacadeService.getAuthByRoleId(roleId.get("roleId"));
        return super.executeApiResponseResponseEntity(rolePermissions);
    }

}
