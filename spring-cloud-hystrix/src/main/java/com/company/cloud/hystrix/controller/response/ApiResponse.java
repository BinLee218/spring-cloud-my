package com.company.cloud.hystrix.controller.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/8/22
 */
@Getter
@Setter
public class ApiResponse<T> {

    private int code;
    private String message;
    private T Date;
}
