package com.company.mybatis.shiro.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author bin.li
 * @date 2018/8/27
 */
@Getter
@Setter
public class LoginUser implements Serializable {

    private Integer userId;
    private String userName;
    private String realName;
}
