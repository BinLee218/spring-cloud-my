package com.company.eureka.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bin.li
 * @date 2020/8/20
 */
@FeignClient("single-provider")
@Service
@RequestMapping()
public interface FeignService {

    @PostMapping(value = "/getUserName")
    @ResponseBody
    String getUserName(@RequestBody UserRequest userRequest);

}
