package com.company.mybatis.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bin.li
 * @date 2020/10/18
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info(" =============== " + getClass().getSimpleName() + ":isAccessAllowed ===============");
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        log.info(httpRequest.getMethod());
        log.info(httpRequest.getRequestURL().toString());
        if (httpRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        if (isLogin(request, response)) {
            return true;
        }
        String authorizationHeader = getAuthzHeader(request);
        log.info("authorizationHeader：" + authorizationHeader);
        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            return false;
        }
        return true;
    }

    private boolean isLogin(ServletRequest request, ServletResponse response) {
        return pathsMatch(getLoginUrl(), request);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info(" =============== " + getClass().getSimpleName() + ":onAccessDenied ===============");
        log.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String authcHeader = getAuthcScheme() + " realm=\"" + getApplicationName() + "\"";
        httpResponse.setHeader(AUTHENTICATE_HEADER, authcHeader);
        return false;
    }
}
