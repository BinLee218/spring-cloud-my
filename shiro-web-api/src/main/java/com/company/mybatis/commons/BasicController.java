package com.company.mybatis.commons;

import org.springframework.http.ResponseEntity;

/**
 * @author bin.li
 * @date 2020/10/22
 */
public class BasicController {

    public <T> ResponseEntity<ApiResponse<T>> getApiResponseResponseEntity(T haha) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(haha);
        return ResponseEntity.ok(apiResponse);
    }
}
