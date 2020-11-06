package com.company.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.Map;
import java.util.UUID;

/**
 * @author bin.li
 * @date 2020/8/25
 * 前置filter
 */
@Slf4j
public class TracePreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Map<String, String> zuulRequestHeaders = requestContext.getZuulRequestHeaders();
        String uuid = UUID.randomUUID().toString();
        zuulRequestHeaders.put("TraceId", uuid);
        requestContext.getRequest().setAttribute("TraceId", uuid);
        log.info("我是：" + FilterConstants.PRE_TYPE);
        return null;
    }
}
