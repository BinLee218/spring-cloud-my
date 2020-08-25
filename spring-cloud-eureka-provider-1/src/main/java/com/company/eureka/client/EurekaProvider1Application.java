package com.company.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProvider1Application.class, args);
	}

}
