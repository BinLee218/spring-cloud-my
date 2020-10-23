package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.commons.jwt.JwtTokenUtils;
import com.company.mybatis.commons.jwt.TokenParam;
import com.company.mybatis.controller.request.LoginRequest;
import com.company.mybatis.controller.response.LoginResponse;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author bin.li
 * @date 2020/9/12
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class LoginController extends BasicController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest loginRequest) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUsername(), loginRequest.getPassword());
        token.setRememberMe(true);
        subject.login(token);
        LoginUser user = subject.getPrincipals().oneByType(LoginUser.class);
        String sessionId = subject.getSession().getId().toString();
        String jwtToken = JwtTokenUtils.createToken(TokenParam.builder()
                .key(JwtTokenUtils.SESSION_KEY)
                .value(sessionId)
                .build());
        LoginResponse loginResponse = LoginResponse.builder()
                .name(user.getUserName())
                .token(jwtToken)
                .build();
        log.info("login success");
        return super.getApiResponseResponseEntity(loginResponse);
    }

    @GetMapping(value = "/user/info")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<LoginResponse>> info(@RequestParam("token") String token) {

        LoginResponse haha = LoginResponse.builder()
                .name("haha")
                .token(token)
                .roles(Lists.newArrayList("admin"))
                .build();
        log.info("info success : " + token);
        return super.getApiResponseResponseEntity(haha);
    }

    @PostMapping(value = "/user/logout")
    public ResponseEntity<ApiResponse<LoginResponse>> logout() {
        LoginResponse haha = LoginResponse.builder()
                .name("haha")
                .token(UUID.randomUUID().toString())
                .build();
        log.info("logout success");
        return super.getApiResponseResponseEntity(haha);
    }

}
