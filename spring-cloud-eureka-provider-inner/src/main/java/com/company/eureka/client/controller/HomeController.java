package com.company.eureka.client.controller;

import com.company.eureka.client.service.TestService;
import com.company.eureka.client.common.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bin.li
 * @date 2020/8/19
 */
@RestController
public class HomeController {

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/home")
    public String home() {
        LogUtil.info("我是/home");
        return client.toString();
    }

    @RequestMapping(value = "/api/home")
    public String apihome() {
        LogUtil.info("我是/api/home");
        return client.toString();
    }

    @PostMapping(value = "/getUserName")
    @ResponseBody
    public String getUserName(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String s = LogUtil.ttl.get();
        LogUtil.info("我是client:" + s);
        testService.test1();
        return "用户名：" + userRequest.getName();
    }

    @RequestMapping(value = "/timeout")
    public String timeout() throws InterruptedException {
        LogUtil.info("我是=timeout");
        Thread.sleep(10000);
        return client.toString();
    }

    @RequestMapping(value = "/throwException")
    public String throwException() {
        LogUtil.info("我是=throwException");
        int i = 1 / 0;
        return client.toString();
    }
}
