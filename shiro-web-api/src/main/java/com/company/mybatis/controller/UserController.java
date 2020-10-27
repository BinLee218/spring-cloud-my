package com.company.mybatis.controller;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.BasicController;
import com.company.mybatis.controller.request.UserAddRequest;
import com.company.mybatis.controller.request.UserRegisterRequest;
import com.company.mybatis.controller.request.UserRequest;
import com.company.mybatis.controller.request.UserUpdateRequest;
import com.company.mybatis.controller.response.LoginResponse;
import com.company.mybatis.controller.response.UserResponse;
import com.company.mybatis.dto.UserPage;
import com.company.mybatis.pojo.User;
import com.company.mybatis.service.UserService;
import com.company.mybatis.shiro.model.LoginUser;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
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

/**
 * @author bin.li
 * @date 2020/10/8
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register")
    @RequiresRoles("admin")
    public ResponseEntity<UserResponse> register(@RequestBody @Validated UserRegisterRequest userRegisterRequest){
        userService.register(userRegisterRequest.getUserName(), userRegisterRequest.getRealName(), userRegisterRequest.getPassword(), 1);
        return ResponseEntity.ok(UserResponse.builder().build());
    }

    @GetMapping(value = "/user/info")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<LoginResponse>> info(@RequestParam("token") String token) {
        Subject subject = SecurityUtils.getSubject();
        LoginUser loginUser = (LoginUser)subject.getPrincipal();
        LoginResponse haha = LoginResponse.builder()
                .name(loginUser.getUserName())
                .token(token)
                .roles(Lists.newArrayList("admin"))
                .build();
        log.info("info success : " + token);
        return super.executeApiResponseResponseEntity(haha);
    }

    @RequestMapping(value = "/users")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<PageInfo<User>>> getAllUsers(@RequestBody @Validated UserRequest userRequest) throws Exception {
        UserPage userPage = new UserPage();
        BeanUtils.copyProperties(userPage, userRequest);
        PageInfo<User> pageInfo = userService.getAll(userPage);
        return super.executeApiResponseResponseEntity(pageInfo);
    }

    @PostMapping(value = "/user/update")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<Void>> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userUpdateRequest);
        return super.executeApiResponseResponseEntity(null);
    }

    @PostMapping(value = "/user/add")
    @RequiresRoles("admin")
    public ResponseEntity<ApiResponse<Void>> addUser(@RequestBody UserAddRequest userAddRequest) {
        log.info(""+userAddRequest.getRoleValue());
        userService.addUser(userAddRequest);
        return super.executeApiResponseResponseEntity(null);
    }
}
