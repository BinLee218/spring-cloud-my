package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;

import java.io.FileInputStream;

public interface HessianService {

    String getHessian();
    User getHessianUser(String name);
    Boolean uploanFile(byte[] fileByte);

}
