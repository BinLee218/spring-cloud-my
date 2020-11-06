package com.company.eureka.client.service;

import com.company.eureka.client.common.ExecuteUtils;
import com.company.eureka.client.common.ThreadTest;
import com.company.eureka.client.trace.TraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/11/6
 */
@Service
@Slf4j
public class TestService {


    public String test1(){
        String s = TraceUtils.ttl.get();
        log.info("我是TestService:" + s);
        for (int i = 0; i < 5; i++) {
            ExecuteUtils.getTtlExecutorService().execute(new ThreadTest());
        }
        return "test1";
    }
}
