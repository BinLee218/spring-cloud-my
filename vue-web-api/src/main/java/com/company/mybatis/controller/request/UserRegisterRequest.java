package com.company.mybatis.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author bin.li
 * @date 2020/10/8
 */
@Getter
@Setter
public class UserRegisterRequest {

    @NotBlank
    @NotEmpty
    private String userName;
    @NotBlank
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String realName;
}
