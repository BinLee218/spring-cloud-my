package com.company.eureka.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin.li
 * @date 2020/8/19
 */
@RestController
@Slf4j
public class HomeController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/home")
    public String home(){
        log.info(client.toString());
        return client.toString();
    }

    @PostMapping(value = "/getUserName")
    @ResponseBody
    public String getUserName(@RequestBody UserRequest userRequest){
        log.info("我是client1");
        return "用户名："+userRequest.getName();
    }
}
