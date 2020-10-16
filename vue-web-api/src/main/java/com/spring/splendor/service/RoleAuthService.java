package com.spring.splendor.service;

import com.spring.splendor.dao.RoleAuthDao;
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


    public List<Integer> findAuthByRoleId(Integer roleId) {
        return roleAuthDao.findAuthByRoleId(roleId);
    }

    public List<Integer> findAllAuthByRoleId(Integer roleId) {
        return roleAuthDao.findAllAuthByRoleId(roleId);
    }
}
