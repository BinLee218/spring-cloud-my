package com.company.mybatis.controller;

import com.company.mybatis.controller.response.MenuResponse;
import com.company.mybatis.facade.HomeFacadeService;
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

    @RequestMapping(value = "/info/menu/{userName}")
    public ResponseEntity<MenuResponse> getMenu(@PathVariable String userName){
        MenuResponse menuResponse = homeFacadeService.getMenuByUserName(userName);
        return ResponseEntity.ok(menuResponse);
    }
}
