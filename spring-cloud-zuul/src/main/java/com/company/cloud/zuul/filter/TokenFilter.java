package com.company.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @author bin.li
 * @date 2020/8/25
 */
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，true=需要过滤 false=不需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("我是Token Filter");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Map<String, String> zuulRequestHeaders = ctx.getZuulRequestHeaders();
        Set<Map.Entry<String, String>> entries = zuulRequestHeaders.entrySet();
        for (Map.Entry<String, String> header : entries) {
            log.info("header-key:"+header.getKey());
            log.info("header-value:"+header.getValue());
        }
        //获取请求的参数
        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            return null;
        }
    }
}
