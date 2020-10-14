package com.company.mybatis.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
//            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        //解决一下跨域问题
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletResponse.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletResponse.getHeader("Access-Control-Request-Headers"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PATCH,DELETE,PUT,OPTIONS");
        httpServletResponse.setHeader("XDomainRequestAllowed", "1");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        /*
         * 该字段可选，用来指定本次预检请求的有效期，单位为秒。上面结果中，有效期是1小时（3600秒），
         * 即允许缓存该条回应3600秒（即1小时），在此期间，不用发出另一条预检请求。
         */
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");


        return super.onAccessDenied(request, response);
    }

    private boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}