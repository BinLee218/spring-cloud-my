package com.company.mybatis.service;

import com.company.mybatis.dao.RoleAuthDao;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class RoleAuthService {

    @Autowired
    private RoleAuthDao roleAuthDao;


    public List<String> findAuthByRoleId(Integer roleId) {
        return roleAuthDao.findAuthByRoleId(roleId);
    }

    public List<Auth> findByRoleId(Integer roleId) {
        return roleAuthDao.findByRoleId(roleId);
    }

    public void deleteRoleAuthByRoleId(Integer roleId) {
        roleAuthDao.deleteRoleAuthByRoleId(roleId);
    }

    public void insertRoleAuth(RoleAuth roleAuth) {
        roleAuthDao.insertSelective(roleAuth);
    }
}
