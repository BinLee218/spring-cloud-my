package com.company.mybatis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author bin.li
 * @date 2020/10/9
 */
@Getter
@Setter
@Builder
@ToString
public class Menu {
    private Integer id;
    private String path;
    private String component;
    private String name;
    private String iconCls;
    private Integer parentId;
    private List<Menu> children;
    private String redirect;
    private Meta meta;


}
