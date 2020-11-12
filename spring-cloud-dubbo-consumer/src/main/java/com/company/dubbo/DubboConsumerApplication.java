package com.company.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.company.dubbo.api.service")
@EnableFeignClients
@EnableDiscoveryClient
public class DubboConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboConsumerApplication.class, args);
	}
}
