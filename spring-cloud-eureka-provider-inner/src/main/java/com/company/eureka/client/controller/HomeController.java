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
        log.info("我是/home");
        return client.toString();
    }

    @RequestMapping(value = "/api/home")
    public String apihome(){
        log.info("我是/api/home");
        return client.toString();
    }

    @PostMapping(value = "/getUserName")
    @ResponseBody
    public String getUserName(@RequestBody UserRequest userRequest){
        log.info("我是client");
        return "用户名："+userRequest.getName();
    }

    @RequestMapping(value = "/timeout")
    public String timeout() throws InterruptedException {
        log.info("我是=timeout");
        Thread.sleep(10000);
        return client.toString();
    }

    @RequestMapping(value = "/throwException")
    public String throwException(){
        log.info("我是=timeout");
        int i = 1/0;
        return client.toString();
    }
}
