package com.company.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

/**
 * @author bin.li
 * @date 2020/8/25
 */
@Slf4j
public class JwtPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
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
        log.info("我是：" + FilterConstants.POST_TYPE);
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getResponseStatusCode() == HttpStatus.OK.value()) {
            log.info("我是：{}, 接口请求成功了，我可以干点事情" , FilterConstants.POST_TYPE);
        }else{
            log.info("我是：{}, 接口请求失败了了，我什么都不能干" , FilterConstants.POST_TYPE);
        }
        return null;
    }
}
