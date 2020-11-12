package com.company.dubbo.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author bin.li
 * @date 2020/11/10
 */
@Getter
@Setter
@Builder
public class User implements Serializable {

    private String userName;
    private String passWord;
}
