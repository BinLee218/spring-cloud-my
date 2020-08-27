package com.company.cloud.zuul.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.apache.HttpClientStatusCodeException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;

/**
 * @author bin.li
 * @date 2020/8/26
 */
@Slf4j
public class GlobalErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        // Remove error code to prevent further error handling in follow up filters
        // 这里有个坑，就是如果不删除这个key的话，
        // 就不会执行 zuul的SendResponseFilter，这个SendResponseFilter是负责写response的，
        // 导致的问题就会没有任何返回值，
        requestContext.remove("throwable");
        requestContext.setSendZuulResponse(false);
        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseBody(HttpStatus.REQUEST_TIMEOUT.getReasonPhrase());
        requestContext.setResponseStatusCode(HttpStatus.REQUEST_TIMEOUT.value());
        return null;
    }
}
