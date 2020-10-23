package com.company.mybatis.shiro.jwt;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @author bin.li
 * @date 2020/10/20
 */
public class JwtSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return null;
    }


}
