package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;

/**
 * @author bin.li
 * @date 2020/11/10
 */
public interface UserService {

    User getUser();
    Boolean createUser();
}
