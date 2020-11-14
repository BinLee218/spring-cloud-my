package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;

public interface HessianService {

    String getHessian();
    User getHessianUser(String name);
    Boolean uploanFile(byte[] fileByte);

}
