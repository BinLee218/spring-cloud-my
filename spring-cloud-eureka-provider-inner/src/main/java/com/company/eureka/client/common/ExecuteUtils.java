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
public class ExecuteUtils {

    private ExecuteUtils() {
    }

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            2,
            10000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue(1),
            (r, executor) -> System.out.println("rejectedExecution"));

    public static ExecutorService getTtlExecutorService() {
        return TtlExecutors.getTtlExecutorService(threadPoolExecutor);
    }
}
