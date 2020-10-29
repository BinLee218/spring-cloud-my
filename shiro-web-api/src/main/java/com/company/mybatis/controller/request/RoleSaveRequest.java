package com.company.mybatis.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/23
 */
@Getter
@Setter
@ToString
public class RoleSaveRequest {

    @NotBlank
    private String roleName;
    @NotBlank
    private String roleValue;
    @NotBlank
    private Integer state;

    private List<String> treeValue;
}
