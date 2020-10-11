package com.company.mybatis.controller;

import com.company.mybatis.controller.request.LoginRequest;
import com.company.mybatis.controller.response.LoginResponse;
import com.company.mybatis.pojo.User;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth/login")
    public ResponseEntity<LoginResponse> login(@Validated LoginRequest loginRequest){
        Subject subject = SecurityUtils.getSubject();
        LoginUser user = subject.getPrincipals().oneByType(LoginUser.class);
        if(Objects.nonNull(user) && user.getUserName().equals(loginRequest.getUsername())) {
            System.out.println(subject.getSession().getId());
            return ResponseEntity.ok(LoginResponse.builder().user(user).build());
        }
        return ResponseEntity.badRequest().build();
    }


}
