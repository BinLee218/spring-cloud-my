package com.company.cloud.zuul.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/8/26
 */
@Component
@Slf4j
public class GlobalFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(Objects.nonNull(cause)){
            log.info("route:{}, error:{}", route, cause.getCause().getMessage());
            for (StackTraceElement stackTraceElement : cause.getCause().getStackTrace()) {
                log.info(stackTraceElement.toString());
            }
        }
        return getFallBackResponse();
    }

    public ClientHttpResponse getFallBackResponse(){
       return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("The Service Unavailable.".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return "我是FallbackProvider";
            }

            @Override
            public void close() {
            }
        };
    }
}
