package com.company.cloud.hystrix.controller;

import com.company.cloud.hystrix.controller.response.ApiResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin.li
 * @date 2020/8/19
 */
@RestController
@RequestMapping(value = "/hystrix")
@Slf4j
public class HystrixController {

    @Autowired
    private DiscoveryClient client;

    /**
     * @return
     * @throws InterruptedException
     * @HystrixCommand中的常用参数 fallbackMethod：指定服务降级处理方法；
     * ignoreExceptions：忽略某些异常，不发生服务降级；
     * commandKey：命令名称，用于区分不同的命令；
     * groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
     * threadPoolKey：线程池名称，用于划分线程池。
     */
    @PostMapping(value = "/timeout")
    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")}
    )
    public ApiResponse<String> hystrixTimeout() throws InterruptedException {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Thread.sleep(3000);
        apiResponse.setCode(200);
        apiResponse.setMessage("success");
        apiResponse.setDate(client.toString());
        return apiResponse;
    }

    /**
     * 服务降级 - 当下面接口中出现了exception时，会执行fallback()
     *
     * @return
     */
    @RequestMapping(value = "/fallback")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "fallback")
    public ApiResponse<String> hystrixFallBack() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("success");
        apiResponse.setDate("hello world");
        return apiResponse;
    }

    public ApiResponse<String> fallback() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        log.info("something is wrong，this is fallback");
        apiResponse.setCode(4000);
        apiResponse.setMessage("success");
        apiResponse.setDate("something is wrong，this is fallback");
        return apiResponse;
    }

    /**
     * ignoreExceptions忽略某些异常降级
     * NullPointerException会被忽略，直接抛出异常
     * IndexOutOfBoundsException 会触发服务降级，执行fallback1方法
     * @return
     */
    @RequestMapping(value = "/ignoreExceptions/{id}")
    @HystrixCommand(fallbackMethod = "fallback1", ignoreExceptions = {NullPointerException.class})
    public ApiResponse<String> ignoreExceptions(@PathVariable Integer id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException("id = 1");
        } else if (id == 2) {
            throw new NullPointerException("id = 2");
        }
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("success");
        apiResponse.setDate("hello ignoreExceptions");
        return apiResponse;
    }

    /**
     * 线程池-降级，核心线程2个，队列2个，
     * 调用方起10个线程，4个线程会通过，6个线程会走降级策略
     * @param id
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/threadPool/{id}")
    @HystrixCommand(fallbackMethod = "fallback1",
            ignoreExceptions = {NullPointerException.class},
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),//（执行的隔离策略）
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//（断路器开关）
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")//（断路器请求阈值）
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "2")
            }
    )
    public ApiResponse<String> threadPoolTest(@PathVariable Integer id) throws InterruptedException {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Thread.sleep(3000);
        System.out.println("hello " + id);
        apiResponse.setCode(200);
        apiResponse.setMessage("success");
        apiResponse.setDate("hello " + id);
        return apiResponse;
    }

    public ApiResponse<String> fallback1(Integer id) {
        System.out.println("something is wrong，this is fallback-1: " + id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(4000);
        apiResponse.setMessage("success");
        apiResponse.setDate("something is wrong，this is fallback-1: " + id);
        return apiResponse;
    }
}
