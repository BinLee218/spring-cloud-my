package com.company.mybatis.commons.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.mybatis.commons.utils.DateUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author bin.li
 * @date 2020/10/20
 */
@Slf4j
public class JwtTokenUtils {
    public static final String SESSION_KEY = "sessionId";
    //UUID.randomUUID().toString() 生成的
    private static final String SECRET_KEY = "a4b34941-ed19-463b-a894-040f7e8a1b84";
    public static String createToken(TokenParam tokenParam){
        Map<String, Object> header = Maps.newHashMap();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        //加密
        return generateToken(header, tokenParam);
    }

    public static String generateToken(Map<String, Object> header, TokenParam tokenParam) {
        Date startDate = DateUtils.getNowDate();
        Date endDate = DateUtils.datePlusMinute(startDate, 30);
        log.info("start date:[{}], end date:[{}]", startDate, endDate);
        // build token
        String token = JWT.create()
                .withHeader(header) // header
                .withIssuer(tokenParam.getIssuser())    //jwt签发者
                .withSubject(tokenParam.getSubject())   //面向对象
                .withAudience(tokenParam.getAudience())    //接收方
                .withIssuedAt(startDate) // 签发时间
                .withExpiresAt(endDate) // 结束时间
                .withClaim(tokenParam.getKey(), tokenParam.getValue())
                .sign(Algorithm.HMAC256(SECRET_KEY)); // signature  签名密钥
        return token;
    }

    /**
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        DecodedJWT jwtDecode = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            jwtDecode = verifier.verify(token);
        } catch (Exception e) {
            log.error("JWT验证出错", e);
        }
        return jwtDecode;
    }

    /**
     * @param token
     * @param payloadKey
     * @return
     */
    public static String getPayloadMapValue(String token, String payloadKey) {
        String payloadValue = null;
        try {
            payloadValue = verifyToken(token).getClaim(payloadKey).asString();
        } catch (Exception e) {
            log.error("JWT出错", e);
        }
        return payloadValue;
    }

    public static String getPayload(String token) {
        byte[] bytes = Base64.getUrlDecoder().decode(token.split("\\.")[1]);
        return new String(bytes);
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        Map<String, Object> header = Maps.newHashMap();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        TokenParam tokenParam = TokenParam.builder()
                .issuser("issuser")
                .audience("audience")
                .subject("subject")
                .secretKey(SECRET_KEY)
                .key("key")
                .value("value")
                .issuedAt(new Date())
                .expiresAt(new Date(new Date().getTime() + 1000L * 60))
                .build();
        String token = generateToken(header, tokenParam);
        System.out.println(token);
        verifyToken(token);

        String tokenss = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsInNlc3Npb25JZCI6ImQxZWQ4MWRkLTljNGYtNDExMy1hYmJiLTRjMDU1ODU1YTRhNiIsImV4cCI6MTYwMzE4NjU2NCwiaWF0IjoxNjAzMTg0NzY0fQ.WYD5hvLdsIwb5RogTKa5YHkofMiGngPSGyCZfrElJ3o";
        System.out.println(verifyToken(tokenss).getPayload());
        byte[] bytes = Base64.getUrlDecoder().decode(verifyToken(tokenss).getPayload());
        System.out.println(new String(bytes));
    }
}
