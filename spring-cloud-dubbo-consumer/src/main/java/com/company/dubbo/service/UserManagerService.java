package com.company.dubbo.service;

import com.company.dubbo.api.dto.User;
import com.company.dubbo.api.service.RestService;
import com.company.dubbo.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/11/10
 */
@Service
public class UserManagerService {

    @DubboReference(protocol = "dubbo")
    private UserService userService;
    @DubboReference(protocol = "dubbo", version = "1.0.0")
    private RestService restService;

    public User getUser(){
        return userService.getUser();
    }

    public User getRestUser(){
        return restService.getUser();
    }


}
