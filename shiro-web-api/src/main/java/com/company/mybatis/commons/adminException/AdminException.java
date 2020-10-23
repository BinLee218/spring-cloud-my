package com.company.mybatis.commons.adminException;

import com.company.mybatis.commons.enums.AdminExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
@Setter
public class AdminException extends RuntimeException {

    private String label;
    private int status;
    private String message;

    public AdminException(AdminExceptionEnum adminExceptionEnum) {
        this.status = adminExceptionEnum.getStatus();
        this.message = adminExceptionEnum.getMessage();
        this.label = adminExceptionEnum.name();
    }

    public AdminException(String label, int status, String message) {
        this.label = label;
        this.status = status;
        this.message = message;
    }

    public static AdminException createRuntimeException(AdminExceptionEnum adminExceptionEnum){
        return new AdminException(adminExceptionEnum);
    }
}
