package com.company.mybatis.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/10/22
 */
@Getter
@Setter
public class UserPage extends PageParam {

    private String userName;

    private String startTime;

    private String endTime;

}
