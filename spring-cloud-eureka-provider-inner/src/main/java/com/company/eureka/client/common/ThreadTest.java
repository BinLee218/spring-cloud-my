package com.company.eureka.client.common;


public class ThreadTest implements Runnable{

    @Override
    public void run() {
        String s = LogUtil.ttl.get();
        LogUtil.info("我是线程啊:" + Thread.currentThread().getName());
    }
}
