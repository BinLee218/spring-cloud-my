package com.company.mybatis.service;

import com.company.mybatis.commons.adminException.AdminException;
import com.company.mybatis.commons.enums.AdminExceptionEnum;
import com.company.mybatis.controller.request.RoleRequest;
import com.company.mybatis.controller.request.RoleSaveRequest;
import com.company.mybatis.controller.request.RoleUpdateRequest;
import com.company.mybatis.dao.RoleDao;
import com.company.mybatis.dto.RolePage;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.pojo.RoleAuth;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleAuthService roleAuthService;
    @Autowired
    private TransactionTemplate masterTransactionTemplate;

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
        List<String> treeValue = roleSaveRequest.getTreeValue();
        masterTransactionTemplate.executeWithoutResult(transactionStatus -> {
            roleDao.insertSelective(role);
            Integer roleId = role.getRoleId();
            List<RoleAuth> roleAuths = treeValue.stream().map(s -> {
                if (NumberUtils.isDigits(s)) {
                    return RoleAuth.builder().roleId(roleId).authValue(s).build();
                }
                return null;
            }).collect(Collectors.toList());
            roleAuths.forEach(roleAuth -> roleAuthService.insertRoleAuth(roleAuth));
        });
    }

    public void updateRole(RoleUpdateRequest roleUpdateRequest) {
        Role primaryKey = roleDao.selectByPrimaryKey(roleUpdateRequest.getRoleId());
        List<String> treeValue = roleUpdateRequest.getTreeValue();
        if(Objects.nonNull(primaryKey)) {
            primaryKey.setRoleName(roleUpdateRequest.getRoleName());
            primaryKey.setRoleValue(roleUpdateRequest.getRoleValue());
            primaryKey.setState(roleUpdateRequest.getState());
            masterTransactionTemplate.executeWithoutResult(transactionStatus -> {
                Integer roleId = primaryKey.getRoleId();
                roleAuthService.deleteRoleAuthByRoleId(roleId);
                List<RoleAuth> roleAuths = treeValue.stream().map(s -> {
                    if (NumberUtils.isDigits(s)) {
                        return RoleAuth.builder().roleId(roleId).authValue(s).build();
                    }
                    return null;
                }).collect(Collectors.toList());
                roleAuths.forEach(roleAuth -> {
                    if (Objects.nonNull(roleAuth)) {
                        roleAuthService.insertRoleAuth(roleAuth);
                    }
                });
                roleDao.updateByPrimaryKeySelective(primaryKey);
            });
            return;
        }
        throw AdminException.createRuntimeException(AdminExceptionEnum.NOT_ROLE_OBJECT_EXCEPTION);
    }

    public List<Role> getAllRole() {
        List<Role> roleList = roleDao.selectActiveAll();
        return roleList;
    }
}
