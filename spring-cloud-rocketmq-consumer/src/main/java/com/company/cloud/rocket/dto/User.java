package com.company.cloud.rocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author bin.li
 * @date 2020/9/8
 */
@Getter
@Setter
@Builder
public class User implements Serializable {

    private String name;
    private Integer result;
}
