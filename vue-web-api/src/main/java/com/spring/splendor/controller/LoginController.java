package com.spring.splendor.controller;

import com.google.common.collect.Lists;
import com.spring.splendor.controller.request.LoginRequest;
import com.spring.splendor.controller.response.LoginResponse;
import com.spring.splendor.controller.response.MenuResponse;
import com.spring.splendor.dto.Menu;
import com.spring.splendor.dto.Meta;
import com.spring.splendor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author bin.li
 * @date 2020/10/14
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        LoginResponse haha = LoginResponse.builder()
                .name("haha")
                .token(UUID.randomUUID().toString())
                .build();
        log.info("login success");
        return ResponseEntity.ok(haha);
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
    public ResponseEntity<LoginResponse> info(@RequestParam("token") String token) {
        System.out.println(token);
        LoginResponse haha = LoginResponse.builder()
                .name("haha")
                .token(token)
                .roles(Lists.newArrayList("admin"))
                .build();
        log.info("info success : " + token);
        return ResponseEntity.ok(haha);
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
