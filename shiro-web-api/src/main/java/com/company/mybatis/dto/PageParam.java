package com.company.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author bin.li
 * @date 2020/10/22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageParam {

    private Integer pageNum;

    private Integer pageSize;
}
