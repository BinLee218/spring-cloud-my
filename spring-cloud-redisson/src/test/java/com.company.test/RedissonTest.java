package com.company.test;

import com.company.spring.redis.SpringBootRedisApplication;
import com.company.spring.redis.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author bin.li
 * @date 2020/9/10
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootRedisApplication.class) //classes指定springboot的启动类
public class RedissonTest {

    @Autowired
    private RedissonService redissonService;

    @Test
    public void test(){
        redissonService.testString();
    }
}
