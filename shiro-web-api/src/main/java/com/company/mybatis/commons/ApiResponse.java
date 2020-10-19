package com.company.mybatis.commons;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class ApiResponse {

    private int subCode = 20000;
    private String message;
}
