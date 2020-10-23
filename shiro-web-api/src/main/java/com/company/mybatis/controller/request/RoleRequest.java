package com.company.mybatis.controller.request;

import com.company.mybatis.dto.PageParam;
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
public class RoleRequest{

    private String roleName;

    private String roleValue;

    private String state;

    private String startTime;

    private String endTime;

    private Integer pageNum;

    private Integer pageSize;

}
