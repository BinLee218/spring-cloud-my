package com.company.mybatis.commons;

import com.company.mybatis.commons.enums.ApiResponseStatusCodeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class ApiResponse<T> {

    private int subCode = ApiResponseStatusCodeEnum.SUCCESS.getSubCode();
    private String subMessage = ApiResponseStatusCodeEnum.SUCCESS.getMessage();
    private T data;
}
