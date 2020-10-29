package com.company.mybatis.controller.request.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
@Setter
@ToString
public class AuthUpdateRequest {

    @NotBlank
    private Integer authId;
    @NotBlank
    private String authName;
    @NotBlank
    private String authValue;
    @NotBlank
    private Integer state;
    @NotBlank
    private Integer appId;
}
