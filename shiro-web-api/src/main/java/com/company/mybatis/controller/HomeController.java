package com.company.mybatis.controller;

import com.company.mybatis.controller.response.MenuResponse;
import com.company.mybatis.facade.HomeFacadeService;
import com.company.mybatis.shiro.model.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin.li
 * @date 2020/10/9
 */
@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @Autowired
    private HomeFacadeService homeFacadeService;

    @RequiresRoles("admin")
    @RequestMapping(value = "/info/menu")
    public ResponseEntity<MenuResponse> getMenu(){
        Subject subject = SecurityUtils.getSubject();
        LoginUser user = subject.getPrincipals().oneByType(LoginUser.class);
        MenuResponse menuResponse = homeFacadeService.getMenuByUserName(user.getUserName());
        return ResponseEntity.ok(menuResponse);
    }
}
