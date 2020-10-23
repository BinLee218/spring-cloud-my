package com.company.mybatis.service;

import com.company.mybatis.commons.adminException.AdminException;
import com.company.mybatis.commons.enums.AdminExceptionEnum;
import com.company.mybatis.controller.request.RoleRequest;
import com.company.mybatis.controller.request.RoleSaveRequest;
import com.company.mybatis.controller.request.RoleUpdateRequest;
import com.company.mybatis.dao.RoleDao;
import com.company.mybatis.dto.RolePage;
import com.company.mybatis.pojo.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role selectByPrimaryKey(Integer roleId){
        return roleDao.selectByPrimaryKey(roleId);
    }

    public PageInfo<Role> getAll(RolePage rolePage) {
        PageHelper.startPage(rolePage.getPageNum(),rolePage.getPageSize());
        List<Role> roleList = roleDao.getAllByPage(rolePage);
        System.out.println(roleList.size());
        return PageInfo.of(roleList);
    }

    public void addRole(RoleSaveRequest roleSaveRequest) {
        Role role = new Role();
        role.setRoleName(roleSaveRequest.getRoleName());
        role.setRoleValue(roleSaveRequest.getRoleValue());
        role.setState(roleSaveRequest.getState());
        roleDao.insertSelective(role);
    }

    public void updateRole(RoleUpdateRequest roleUpdateRequest) {
        Role primaryKey = roleDao.selectByPrimaryKey(roleUpdateRequest.getRoleId());
        if(Objects.nonNull(primaryKey)) {
            primaryKey.setRoleName(roleUpdateRequest.getRoleName());
            primaryKey.setRoleValue(roleUpdateRequest.getRoleValue());
            primaryKey.setState(roleUpdateRequest.getState());
            roleDao.updateByPrimaryKeySelective(primaryKey);
            return;
        }
        throw AdminException.createRuntimeException(AdminExceptionEnum.NOT_ROLE_OBJECT_EXCEPTION);
    }
}
