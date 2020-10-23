package com.company.mybatis.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/10/22
 */
@Getter
@Setter
public class RolePage extends PageParam {

    private String roleName;

    private String roleValue;

    private String startTime;

    private String endTime;

}
