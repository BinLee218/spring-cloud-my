package com.company.mybatis.commons.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author bin.li
 * @date 2020/10/20
 */
@Getter
@Setter
@Builder
public class TokenParam {

    /**
     *  jwt签发者
     */
    private String issuser;
    /**
     *  面向对象
     */
    private String subject;
    /**
     *  接收方
     */
    private String audience;
    /**
     *  签发时间
     */
    private Date issuedAt;
    /**
     *  结束时间
     */
    private Date expiresAt;
    /**
     *  负载中的key
     */
    private String key;
    /**
     *  负载中key所对应的value
     */
    private String value;
    /**
     *  签名密钥
     */
    private String secretKey;
}
