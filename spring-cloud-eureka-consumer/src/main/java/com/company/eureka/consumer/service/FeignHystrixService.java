package com.company.eureka.consumer.service;

import com.company.eureka.consumer.controller.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bin.li
 * @date 2020/8/22
 */
@RequestMapping()
@FeignClient("hystrix-provider")
@Service
public interface FeignHystrixService {

    @PostMapping(value = "/hystrix/timeout")
    ApiResponse<String> hystrixTimeout();

    @RequestMapping(value = "/hystrix/fallback")
    @ResponseBody
    ApiResponse<String> hystrixFallBack();

    @RequestMapping(value = "/hystrix/ignoreExceptions/{id}")
    ApiResponse<String> hystrixIgnoreExceptions(@PathVariable Integer id);
}

