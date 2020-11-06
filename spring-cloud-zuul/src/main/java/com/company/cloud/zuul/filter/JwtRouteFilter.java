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
 * 路由filter
 */
@Slf4j
public class JwtRouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
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
        Map<String, String> zuulRequestHeaders = requestContext.getZuulRequestHeaders();
        System.out.println(zuulRequestHeaders.get("TraceId"));
        log.info("我是：" + FilterConstants.ROUTE_TYPE);
        return null;
    }
}
