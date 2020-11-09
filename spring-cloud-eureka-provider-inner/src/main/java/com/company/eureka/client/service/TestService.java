package com.company.eureka.client.service;

import com.company.eureka.client.common.ExecuteUtil;
import com.company.eureka.client.common.ThreadTest;
import com.company.eureka.client.common.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/11/6
 */
@Service
public class TestService {


    public String test1(){
        String s = LogUtil.ttl.get();
        LogUtil.info("我是TestService:" + s);
        for (int i = 0; i < 5; i++) {
            ExecuteUtil.getTtlExecutorService().execute(new ThreadTest());
        }
        return "test1";
    }
}
