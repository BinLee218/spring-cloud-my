package com.company.mybatis.service;

import com.company.mybatis.dao.UserRoleDao;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;


    public Integer findRoleByUserId(Integer userId) {
        return userRoleDao.findRoleByUserId(userId);
    }

    public void saveUserRole(Integer userId, Integer roleId) {
        UserRole record = new UserRole();
        record.setRoleId(roleId);
        record.setUserId(userId);
        userRoleDao.insertSelective(record);
    }

    public UserRole findByUserId(Integer userId) {
        return userRoleDao.findByUserId(userId);
    }

    public void updateByPrimaryKeySelective(UserRole userRole) {
        userRoleDao.updateByPrimaryKeySelective(userRole);
    }
}
