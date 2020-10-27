package com.company.mybatis.service;

import com.company.mybatis.commons.adminException.AdminException;
import com.company.mybatis.commons.enums.AdminExceptionEnum;
import com.company.mybatis.controller.request.LoginRequest;
import com.company.mybatis.controller.request.UserAddRequest;
import com.company.mybatis.controller.request.UserRegisterRequest;
import com.company.mybatis.controller.request.UserUpdateRequest;
import com.company.mybatis.dao.UserDao;
import com.company.mybatis.dto.UserPage;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.pojo.User;
import com.company.mybatis.pojo.UserRole;
import com.company.mybatis.shiro.model.LoginUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class UserService {

    public static final String DEFAULT_PASSWORD = "123456";

    @Autowired
    private TransactionTemplate masterTransactionTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleService userRoleService;

    public User login(String username, String pwd) {
        User user = userDao.findUserByUserName(username);
        if (Objects.nonNull(user)) {
            String password = pwd;
            String salt = user.getSalt();
            password = salt + password + salt;
            String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
            if (user.getPassword().equals(md5)) {
                return user;
            }
        }
        return null;
    }

    public void register(String userName, String realName, String pwd, Integer status) {
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = salt + pwd + salt;
        User user = User.builder()
                .userName(userName)
                .realName(realName)
                .status(status)
                .salt(salt)
                .password(DigestUtils.md5DigestAsHex(password.getBytes()))
                .build();
        userDao.insertSelective(user);
    }

    public User findUserByName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public PageInfo<User> getAll(UserPage userPage) {
        PageHelper.startPage(userPage.getPageNum(),userPage.getPageSize());
        List<User> roleList = userDao.getAllByPage(userPage);
        return PageInfo.of(roleList);
    }

    public void updateLastLoginTimeAndLog(LoginUser user) {

    }

    public void updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userDao.selectByPrimaryKey(userUpdateRequest.getUserId());
        UserRole userRole = userRoleService.findByUserId(userUpdateRequest.getUserId());
        if(Objects.nonNull(user)){
            user.setRealName(userUpdateRequest.getRealName());
            user.setUserName(userUpdateRequest.getUserName());
            user.setStatus(userUpdateRequest.getStatus());
            userRole.setRoleId(userUpdateRequest.getRoleId());
            masterTransactionTemplate.executeWithoutResult(new Consumer<TransactionStatus>() {
                @Override
                public void accept(TransactionStatus transactionStatus) {
                    userRoleService.updateByPrimaryKeySelective(userRole);
                    userDao.updateByPrimaryKeySelective(user);
                }
            });

            return;
        }
        throw AdminException.createRuntimeException(AdminExceptionEnum.NOT_USER_OBJECT_EXCEPTION);
    }

    public void addUser(UserAddRequest userAddRequest) {
        masterTransactionTemplate.executeWithoutResult(new Consumer<TransactionStatus>() {
            @Override
            public void accept(TransactionStatus transactionStatus) {
                register(userAddRequest.getUserName(), userAddRequest.getRealName(), DEFAULT_PASSWORD, userAddRequest.getStatus());
                User user = userDao.findUserByUserName(userAddRequest.getUserName());
                userRoleService.saveUserRole(user.getUserId(), userAddRequest.getRoleValue());
            }
        });
    }
}
