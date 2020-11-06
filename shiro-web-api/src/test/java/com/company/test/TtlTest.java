package com.company.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bin.li
 * @date 2020/11/5
 */
@Slf4j
public class TtlTest {

    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws InterruptedException {
        final TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();
        ttl.set("main");
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                10,
                10000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejectedExecution");
            }
        });

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(ttl.get());
                    ttl.set(Thread.currentThread().getName());
                    System.out.println(ttl.get());

                }
            });
        }
        Thread.sleep(1000);
        System.out.println(ttl.get());
    }
}
