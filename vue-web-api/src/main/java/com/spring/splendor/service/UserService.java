package com.spring.splendor.service;

import com.spring.splendor.controller.request.UserRegisterRequest;
import com.spring.splendor.dao.UserDao;
import com.spring.splendor.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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

    public void register(UserRegisterRequest userRegisterRequest) {
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = salt + userRegisterRequest.getPassword() + salt;
        User user = User.builder()
                .userName(userRegisterRequest.getUserName())
                .realName(userRegisterRequest.getRealName())
                .salt(salt)
                .password(DigestUtils.md5DigestAsHex(password.getBytes()))
                .build();
        userDao.insertSelective(user);
    }

    public User findUserByName(String userName) {
        return userDao.findUserByUserName(userName);
    }
}
