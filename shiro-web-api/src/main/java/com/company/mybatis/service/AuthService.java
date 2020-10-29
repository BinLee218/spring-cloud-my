package com.company.mybatis.service;

import com.company.mybatis.controller.request.auth.AuthSaveRequest;
import com.company.mybatis.controller.request.auth.AuthUpdateRequest;
import com.company.mybatis.dao.AuthDao;
import com.company.mybatis.dto.AuthPage;
import com.company.mybatis.pojo.Applications;
import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;
    @Autowired
    private TransactionTemplate masterTransactionTemplate;
    @Autowired
    private ApplicationsService applicationsService;


    public List<Auth> findAuthByValues(List<String> authIds) {
        return authDao.findAuthByIds(authIds);
    }

    public List<Auth> findAllAuthByIds(List<Integer> roleAuthIds) {
        return authDao.findAllAuthByIds(roleAuthIds);
    }

    public List<Auth> findAuthByAppId(Integer appId) {
        return authDao.findAuthByAppId(appId);
    }

    public PageInfo<Auth> getAll(AuthPage authPage) {
        PageHelper.startPage(authPage.getPageNum(),authPage.getPageSize());
        List<Auth> authList = authDao.getAllByPage(authPage);
        return PageInfo.of(authList);
    }

    public void addAuth(AuthSaveRequest authSaveRequest) {
        Auth auth = new Auth();
        auth.setAuthName(authSaveRequest.getAuthName());
        auth.setAuthValue(authSaveRequest.getAuthValue());
        auth.setState(authSaveRequest.getState());
        auth.setAppId(authSaveRequest.getAppId());
        masterTransactionTemplate.executeWithoutResult(new Consumer<TransactionStatus>() {
            @Override
            public void accept(TransactionStatus transactionStatus) {
                authDao.insertSelective(auth);
            }
        });
    }

    public void updateAuth(AuthUpdateRequest authUpdateRequest) {
        Auth auth = authDao.selectByPrimaryKey(authUpdateRequest.getAuthId());
        if (Objects.nonNull(auth)) {
            auth.setAppId(authUpdateRequest.getAppId());
            auth.setAuthName(authUpdateRequest.getAuthName());
            auth.setAuthValue(authUpdateRequest.getAuthValue());
            auth.setState(authUpdateRequest.getState());
            masterTransactionTemplate.executeWithoutResult(new Consumer<TransactionStatus>() {
                @Override
                public void accept(TransactionStatus transactionStatus) {
                    authDao.updateByPrimaryKeySelective(auth);
                }
            });
        }
    }

    public List<Applications> getAllApp() {
        return applicationsService.selectAll();
    }
}
