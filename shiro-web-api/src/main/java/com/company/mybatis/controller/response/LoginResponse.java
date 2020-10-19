package com.company.mybatis.controller.response;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.pojo.User;
import com.company.mybatis.shiro.model.LoginUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/8
 */
@Getter
@Setter
@Builder
public class LoginResponse extends ApiResponse {

    private String name;
    private List<String> roles;
    private String token;
}
