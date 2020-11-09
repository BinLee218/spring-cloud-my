package com.company.eureka.client.common;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bin.li
 * @date 2020/11/6
 */
public class ExecuteUtil {

    private ExecuteUtil() {
    }

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            10,
            10000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue(10),
            (r, executor) -> System.out.println("rejectedExecution"));

    public static ExecutorService getTtlExecutorService() {
        return TtlExecutors.getTtlExecutorService(threadPoolExecutor);
    }
}
