package com.company.mybatis.interceptor;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bin.li
 * @date 2020/10/19
 */
public class ShiroSubjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        Subject subject = new Subject.Builder().sessionId(authorization).buildSubject();
        ThreadContext.bind(subject);
        return true;
    }
}
