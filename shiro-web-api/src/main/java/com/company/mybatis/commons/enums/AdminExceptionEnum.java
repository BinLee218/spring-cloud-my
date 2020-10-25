package com.company.mybatis.commons.enums;

import lombok.Getter;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
public enum AdminExceptionEnum {

    NOT_ROLE_OBJECT_EXCEPTION(50000, "角色信息不存在"),
    NOT_USER_OBJECT_EXCEPTION(50001, "用户信息不存在"),
    LOGIN_ERROR_EXCEPTION(40001, "登录失败，用户/密码错误"),
    ;

    private int status;
    private String message;

    AdminExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
