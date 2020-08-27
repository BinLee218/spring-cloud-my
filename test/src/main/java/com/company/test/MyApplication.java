package com.company.test;

import lombok.SneakyThrows;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) throws InterruptedException {
//		SpringApplication.run(MyApplication.class, args);
		CountDownLatch countDownLatch = new CountDownLatch(1);

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				10,
				10,
				10000,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "我是线程");
			}
		}, new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("rejectedExecution");
			}
		});

		for (int i = 0; i < 10; i++) {
			threadPoolExecutor.execute(new Runnable() {
				@SneakyThrows
				@Override
				public void run() {
					countDownLatch.await();
					HttpPost httpPost = new HttpPost("http://localhost:9094/hystrix/threadPool/100");
					CloseableHttpClient build = HttpClientBuilder.create().build();
					CloseableHttpResponse execute = build.execute(httpPost);
					String s = EntityUtils.toString(execute.getEntity(), "utf-8");
					System.out.println(s);
				}
			});
		}
		Thread.sleep(10000);
		countDownLatch.countDown();
	}
}
