package com.spring.splendor.service;

import com.spring.splendor.dao.AuthDao;
import com.spring.splendor.pojo.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;


    public List<Auth> findAuthByIds(List<Integer> roleAuthIds) {
        return authDao.findAuthByIds(roleAuthIds);
    }

    public List<Auth> findChildrenById(Integer authId) {
        return authDao.findChildrenById(authId);
    }

    public List<Auth> findAllAuthByIds(List<Integer> roleAuthIds) {
        return authDao.findAllAuthByIds(roleAuthIds);
    }
}
