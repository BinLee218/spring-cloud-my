package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.controller.request.RoleRequest;
import com.company.mybatis.controller.request.RoleSaveRequest;
import com.company.mybatis.controller.request.RoleUpdateRequest;
import com.company.mybatis.dto.RolePage;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.service.RoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/roles")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<PageInfo<Role>>> getAllRoles(@RequestBody RoleRequest roleRequest) throws Exception {
        RolePage rolePage = new RolePage();
        BeanUtils.copyProperties(rolePage, roleRequest);
        log.info(rolePage.getStartTime());
        log.info(rolePage.getEndTime());
        PageInfo<Role> pageInfo = roleService.getAll(rolePage);
        return super.getApiResponseResponseEntity(pageInfo);

    }

    @PostMapping(value = "/addRole")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<String>> addRole(@RequestBody RoleSaveRequest roleSaveRequest) {
        roleService.addRole(roleSaveRequest);
        return super.getApiResponseResponseEntity("");
    }

    @PostMapping(value = "/updateRole")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<String>> updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        roleService.updateRole(roleUpdateRequest);
        return super.getApiResponseResponseEntity("");
    }

}
