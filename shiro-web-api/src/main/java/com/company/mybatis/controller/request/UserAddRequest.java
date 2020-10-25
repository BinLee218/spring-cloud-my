package com.company.mybatis.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
@Setter
@ToString
public class UserAddRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String realName;
    @NotBlank
    private Integer status;
}
