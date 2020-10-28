package com.company.mybatis.dto.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/28
 */
@Getter
@Setter
@Builder
public class RolePermission {

    private String id;
    private String label;
    private List<RolePermission> children;
    private Boolean disabled;

}
