package com.company.eureka.consumer.controller;

import com.company.eureka.consumer.controller.response.ApiResponse;
import com.company.eureka.consumer.service.FeignHystrixService;
import com.company.eureka.consumer.service.FeignService;
import com.company.eureka.consumer.service.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author bin.li
 * @date 2020/8/19
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignService feignService;

    @Autowired
    private FeignHystrixService feignHystrixService;

    /**
     * http://localhost:9091/goHome
     * @return
     */
    @GetMapping(value = "/goHome")
    public String goHome() {
        ServiceInstance choose = loadBalancerClient.choose("single-provider");
        return restTemplate.getForObject("http://" + choose.getInstanceId() + "/home", String.class);
    }

    /**
     * http://localhost:9091/user/name
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/user/name")
    public String getUserName() throws JsonProcessingException {
        ServiceInstance choose = loadBalancerClient.choose("single-provider");
        log.info("choose:{}", choose.getInstanceId() );
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("name", "我是哈哈");
        String name = objectMapper.writeValueAsString(map);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(name, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://" + choose.getInstanceId() + "/getUserName", requestEntity , String.class);
        return responseEntity.getBody();
    }

    @PostMapping(value = "/feign/user/name")
    public String getUserNames(String name){
        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        String userName = feignService.getUserName(userRequest);
        return userName;
    }

    /**
     * 这里想实现timeout的情况，需要修改feign的超时时间包括连接超时和读取超时，
     * 而feign的超时依赖了ribbon的超时配置，
     * 这里调用的服务的降级是2000不返回就超时，
     * 所以这个feign的超时配置要大于2000，不然就会提前read time out
     * @return
     */
    @RequestMapping(value = "/timeout")
    public String hystrixTimeout(){
        ApiResponse<String> s = feignHystrixService.hystrixTimeout();
        return s.getDate();
    }

    /**
     * 直接降级的方式
     * @return
     */
    @RequestMapping(value = "/fallback")
    public String hystrixFallback(){
        ApiResponse<String> stringApiResponse = feignHystrixService.hystrixFallBack();
        return stringApiResponse.getDate();
    }

    /**
     * 忽略某些异常的降级，
     * NullPointerException会被忽略，直接抛出异常
     * IndexOutOfBoundsException 会触发服务降级，
     * @param id
     * @return
     */
    @RequestMapping(value = "/ignoreExceptions/{id}")
    public String ignoreExceptions(@PathVariable Integer id) {
        ApiResponse<String> stringApiResponse = feignHystrixService.hystrixIgnoreExceptions(id);
        return stringApiResponse.getDate();

    }
}
