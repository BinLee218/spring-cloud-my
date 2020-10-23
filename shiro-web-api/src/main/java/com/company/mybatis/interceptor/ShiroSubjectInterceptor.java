package com.company.mybatis.interceptor;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.utils.GsonUtils;
import com.company.mybatis.commons.enums.ApiResponseStatusCodeEnum;
import com.company.mybatis.commons.jwt.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
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
        String sessionId = JwtTokenUtils.getPayloadMapValue(authorization, JwtTokenUtils.SESSION_KEY);
        if(StringUtils.isBlank(sessionId)){
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setSubCode(ApiResponseStatusCodeEnum.TOKEN_EXPIRED.getSubCode());
            apiResponse.setSubMessage(ApiResponseStatusCodeEnum.TOKEN_EXPIRED.getMessage());
            response.getWriter().write(GsonUtils.getGsonWithOutConfig().toJson(apiResponse));
            response.flushBuffer();
            return false;
        }
        Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
        ThreadContext.bind(subject);
        return true;
    }
}
