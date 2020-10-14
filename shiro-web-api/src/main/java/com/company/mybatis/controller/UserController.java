package com.company.mybatis.controller;

import com.company.mybatis.controller.request.UserRegisterRequest;
import com.company.mybatis.controller.response.UserResponse;
import com.company.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin.li
 * @date 2020/10/8
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Validated UserRegisterRequest userRegisterRequest){
        userService.register(userRegisterRequest);
        return ResponseEntity.ok(UserResponse.builder().build());
    }
}
