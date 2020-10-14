package com.company.mybatis.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author bin.li
 * @date 2020/10/8
 */
@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
