package com.company.mybatis.commons.enums;

/**
 * @author bin.li
 * @date 2020/10/21
 */
public enum ApiResponseStatusCodeEnum {
    SUCCESS(20000, ""),
    ILLEGAL_TOKEN(50008, ""),
    OTHER_CLIENTS_LOGGED_IN(50012, ""),
    TOKEN_EXPIRED(50014, "TOKEN EXPIRED");

    private int subCode;
    private String message;

    ApiResponseStatusCodeEnum(int subCode, String message) {
        this.subCode = subCode;
        this.message = message;
    }

    public int getSubCode() {
        return subCode;
    }

    public String getMessage() {
        return message;
    }
}
