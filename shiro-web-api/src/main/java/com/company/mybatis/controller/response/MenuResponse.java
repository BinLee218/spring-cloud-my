package com.company.mybatis.controller.response;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.dto.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author bin.li
 */
@Getter
@Setter
@Builder
@ToString
public class MenuResponse extends ApiResponse {
    private List<Menu> menus;
}
