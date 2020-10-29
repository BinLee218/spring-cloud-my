package com.company.mybatis.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/10/22
 */
@Getter
@Setter
public class AuthPage extends PageParam {

    private String authName;

    private String startTime;

    private String endTime;

}
