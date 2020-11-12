package com.company.dubbo.api.service;

import com.company.dubbo.api.dto.User;

import javax.ws.rs.QueryParam;

/**
 * @author bin.li
 * @date 2020/11/11
 */
public interface RestService {

    User getUser();
    String param(String param);
    String params(int a,  String b);
}
