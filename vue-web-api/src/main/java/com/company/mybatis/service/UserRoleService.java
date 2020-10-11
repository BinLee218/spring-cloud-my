package com.company.mybatis.service;

import com.company.mybatis.dao.UserRoleDao;
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
}
