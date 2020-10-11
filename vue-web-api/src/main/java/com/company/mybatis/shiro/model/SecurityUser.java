package com.company.mybatis.shiro.model;


import com.company.mybatis.pojo.Auth;
import com.company.mybatis.pojo.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author bin.li
 * @date 2018/8/27
 */
@Getter
@Setter
public class SecurityUser implements Serializable {
    private int accountId;
    private LoginUser loginUser;
    private List<Role> roles;
    private List<Auth> rights;
    private List<Integer> organizations;
}
