package com.spring.splendor.service;

import com.spring.splendor.dao.RoleDao;
import com.spring.splendor.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
