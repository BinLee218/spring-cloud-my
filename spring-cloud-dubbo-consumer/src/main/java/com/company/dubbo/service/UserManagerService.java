package com.company.dubbo.service;

import com.company.dubbo.api.dto.User;
import com.company.dubbo.api.service.HessianService;
import com.company.dubbo.api.service.RestService;
import com.company.dubbo.api.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import sun.nio.cs.FastCharsetProvider;

import java.io.File;
import java.io.IOException;

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
    @DubboReference(protocol = "hessian", check = false)
    private HessianService hessianService;

    public User getUser() {
        return userService.getUser();
    }

    public User getRestUser() {
        return restService.getUser();
    }

    public String getHessian() {
        return hessianService.getHessian();
    }

    public User getHessianUser(String name) {
        return hessianService.getHessianUser(name);
    }

    public Boolean upload(File file) {
        try {
            return hessianService.uploanFile(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
