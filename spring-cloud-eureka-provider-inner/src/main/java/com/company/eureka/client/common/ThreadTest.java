package com.company.eureka.client.common;

import com.company.eureka.client.trace.TraceUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadTest implements Runnable{

    @Override
    public void run() {
        String s = TraceUtils.ttl.get();
        log.info("我是线程啊:" + s);
    }
}
