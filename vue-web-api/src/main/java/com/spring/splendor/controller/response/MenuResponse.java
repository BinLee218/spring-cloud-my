package com.spring.splendor.controller.response;

import com.spring.splendor.commons.ApiResponse;
import com.spring.splendor.dto.Menu;
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
