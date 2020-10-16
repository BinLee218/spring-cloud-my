package com.spring.splendor.controller.response;

import com.spring.splendor.commons.ApiResponse;
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
