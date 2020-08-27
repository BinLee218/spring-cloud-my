package com.company.cloud.zuul.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bin.li
 * @date 2020/8/25
 */
@Configuration
public class FilterConfig {

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
    @Bean
    public JwtPreFilter jwtPreFilter(){
        return new JwtPreFilter();
    }
    @Bean
    public JwtPostFilter jwtPostFilter(){
        return new JwtPostFilter();
    }
    @Bean
    public JwtRouteFilter jwtRouteFilter(){
        return new JwtRouteFilter();
    }
    @Bean
    public GlobalErrorFilter globalErrorFilter(){
        return new GlobalErrorFilter();
    }
}
