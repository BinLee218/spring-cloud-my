package com.company.spring.redis.controller;

import com.company.spring.redis.service.RedissonService;
import jodd.util.concurrent.ThreadFactoryBuilder;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bin.li
 * @date 2020/9/10
 */
@RestController
@RequestMapping(value = "/redisson")
public class RedissonController {


    @Autowired
    private RedissonService redissonService;

    @RequestMapping(value = "lock")
    public ResponseEntity<String> testLock() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").get();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                10,
                0,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10), namedThreadFactory);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> redissonService.testLock());
        }
        return ResponseEntity.ok("ok");
    }


}
