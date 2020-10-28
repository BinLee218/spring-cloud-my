package com.company.mybatis.service;

import com.company.mybatis.dao.AuthDao;
import com.company.mybatis.pojo.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;


    public List<Auth> findAuthByIds(List<Integer> authIds) {
        return authDao.findAuthByIds(authIds);
    }

    public List<Auth> findChildrenById(Integer authId) {
        return authDao.findChildrenById(authId);
    }

    public List<Auth> findAllAuthByIds(List<Integer> roleAuthIds) {
        return authDao.findAllAuthByIds(roleAuthIds);
    }

    public List<Auth> findAuthByAppId(Integer appId) {
        return authDao.findAuthByAppId(appId);
    }
}
