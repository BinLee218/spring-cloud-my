package com.company.spring.redis.service;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author bin.li
 * @date 2020/9/10
 */
@Service
@Slf4j
public class RedissonService {

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void testLock(){
        RLock testLock = redisson.getLock("testLock");
        try {
            log.info("我要准备加锁了{}", Thread.currentThread().getName());
            testLock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("我要准备释放锁了{}", Thread.currentThread().getName());
            testLock.unlock();
        }
    }

    public void testString(){
        //字符串操作
        /**
         * 字符串操作
         */
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("key001", "key001");
        System.out.println(valueOperations.get("key001"));
        valueOperations.set("key:001", "key001", 10000);
        System.out.println(valueOperations.get("key:001",10000, (10000 + "key001".length())));
        valueOperations.set("key:002", "key002", 100, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("key:002"));
        valueOperations.setBit("key:bit", 10, Boolean.FALSE);
        System.out.println(valueOperations.getBit("key:bit", 10));
        Long increment = valueOperations.increment("key:increment");
        System.out.println(increment);
        //key存在才更新
        System.out.println(valueOperations.setIfPresent("key:001", "key001key001"));
        System.out.println(valueOperations.setIfPresent("key:003", "key001key001"));
        String andSet = valueOperations.getAndSet("key:001", "key:001");
        System.out.println(andSet);
        System.out.println(stringRedisTemplate.delete("age"));

        //hash操作
        /**
         * hash操作
         */
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.put("hash:key", "name","你好");
        opsForHash.put("hash:key", "age","29");
        opsForHash.put("hash:key", "gender","男");
        opsForHash.put("hash:key", "address","三里屯");
        System.out.println(opsForHash.delete("hash:key", "address"));
        System.out.println(opsForHash.lengthOfValue("hash:key", "name"));
        System.out.println(opsForHash.lengthOfValue("hash:key", "age"));
        System.out.println(opsForHash.lengthOfValue("hash:key", "gender"));
        System.out.println(opsForHash.lengthOfValue("hash:key", "address"));

        /**
         * 队列操作
         */
        stringRedisTemplate.delete("list:key");
        stringRedisTemplate.delete("list:key1");
        //左进左出  栈
        //      1         2           3         4
        //  你好世界4   你好世界3   你好世界2   你好世界1
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        opsForList.leftPush("list:key", "你好世界1");
        opsForList.leftPush("list:key", "你好世界2");
        opsForList.leftPush("list:key", "你好世界3");
        opsForList.leftPush("list:key", "你好世界4");
        System.out.println(opsForList.leftPop("list:key"));
        //右近右出  栈
        //      1         2           3         4
        //  你好世界1   你好世界2   你好世界3   你好世界4
        opsForList.rightPush("list:key1", "你好世界1");
        opsForList.rightPush("list:key1", "你好世界2");
        opsForList.rightPush("list:key1", "你好世界3");
        opsForList.rightPush("list:key1", "你好世界4");
        System.out.println(opsForList.rightPop("list:key1"));
        //左进右出  队列
        opsForList.leftPush("list:key2", "你好世界1");
        opsForList.leftPush("list:key2", "你好世界2");
        opsForList.leftPush("list:key2", "你好世界3");
        opsForList.leftPush("list:key2", "你好世界4");
        System.out.println(opsForList.rightPop("list:key2"));

        /**
         * SET集合操作
         */
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        //插入
        opsForSet.add("set:key", "set1");
        opsForSet.add("set:key", "set2");
        opsForSet.add("set:key", "set3");
        opsForSet.add("set:key", "set4");
        opsForSet.add("set:key", "set5");
        opsForSet.add("set:key1", "set11");
        opsForSet.add("set:key1", "set22");
        opsForSet.add("set:key1", "set3");
        opsForSet.add("set:key1", "set44");
        opsForSet.add("set:key1", "set5");
        //value是否存在
        System.out.println(opsForSet.isMember("set:key", "set1"));
        //所有value
        Set<String> members = opsForSet.members("set:key");
        for (String member : members) {
            System.out.println(member);
        }
        //弹出一个数据
//        System.out.println(opsForSet.pop("set:key"));
        //删除
//        System.out.println(opsForSet.remove("set:key", "set5"));
        //取两个set不同的数据
        System.out.println("=======分割线=======");
        Set<String> difference = opsForSet.difference("set:key", "set:key1");
        difference.forEach(System.out::println);
        //取不同数据放到新set
        System.out.println(opsForSet.differenceAndStore("set:key", "set:key1", "set:key2"));

        //取两边相同的数据
        System.out.println("=======分割线=======");
        Set<String> intersect = opsForSet.intersect("set:key", "set:key1");
        intersect.forEach(System.out::println);
        //取相同数据放到新set
        System.out.println(opsForSet.intersectAndStore("set:key", "set:key1", "set:key3"));


        //合并两个集合，并且去重
        System.out.println("=======分割线=======");
        Set<String> union = opsForSet.union("set:key", "set:key1");
        union.forEach(System.out::println);
        //合并去重放到新set
        System.out.println(opsForSet.unionAndStore("set:key", "set:key1", "set:key4"));
        System.out.println("=======分割线=======");


        /**
         * zset操作
         */
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        opsForZSet.add("zset:key","key1",32.4);
        opsForZSet.add("zset:key","key2",22.4);
        opsForZSet.add("zset:key","key3",42.4);
        opsForZSet.add("zset:key","key4",62.4);
        opsForZSet.add("zset:key","key5",12.4);
        opsForZSet.zCard("zset:key");
    }















}

