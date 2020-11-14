package com.company.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.company.dubbo.api.service")
public class DubboProviderApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboProviderApplication.class)
				.properties()
//				.web(WebApplicationType.NONE)
				.run(args);
	}
}
