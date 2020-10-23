package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.commons.jwt.JwtTokenUtils;
import com.company.mybatis.commons.jwt.TokenParam;
import com.company.mybatis.controller.request.LoginRequest;
import com.company.mybatis.controller.response.LoginResponse;
import com.company.mybatis.controller.response.MenuResponse;
import com.company.mybatis.dto.Menu;
import com.company.mybatis.dto.Meta;
import com.company.mybatis.pojo.Role;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest loginRequest){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUsername(),loginRequest.getPassword());
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

    @PostMapping(value = "/user/logout")
    public ResponseEntity<LoginResponse> logout() {
        LoginResponse haha = LoginResponse.builder()
                .name("haha")
                .token(UUID.randomUUID().toString())
                .build();
        log.info("logout success");
        return ResponseEntity.ok(haha);
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

    @GetMapping(value = "/user/menu")
    public ResponseEntity<MenuResponse> menu(@RequestParam("token") String token) {
        List<Menu> menus = Lists.newArrayList();
        Menu menu = Menu.builder()
                .path("/")
                .redirect("/dashboard")
                .name("Test")
                .children(Lists.newArrayList(Menu.builder().name("Dashboard").path("dashboard").meta(Meta.builder().title("后台生成的").icon("dashboard").build()).build()))
                .build();
        menus.add(menu);
        MenuResponse menuResponse = MenuResponse.builder().menus(menus).build();
        log.info("menu success :" + token);
        return ResponseEntity.ok(menuResponse);
    }

}
