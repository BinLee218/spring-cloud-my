package com.company.mybatis.shiro.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.lang.annotation.Target;

/**
 * @author bin.li
 * @date 2018/8/27
 */
@Getter
@Setter
@ToString
public class LoginUser implements Serializable {

    private Integer id;
    private String userName;
    private String realName;
}
