package com.company.mybatis.commons.enums;

import lombok.Getter;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
public enum AdminExceptionEnum {


    NOT_ROLE_OBJECT_EXCEPTION(40000, "角色信息不存在");

    private int status;
    private String message;

    AdminExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
