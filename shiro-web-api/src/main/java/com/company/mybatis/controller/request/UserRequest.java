package com.company.mybatis.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bin.li
 * @date 2020/10/22
 */
@Getter
@Setter
@ToString
public class UserRequest {

    private String userName;

    private String startTime;

    private String endTime;

    private Integer pageNum;

    private Integer pageSize;

}
