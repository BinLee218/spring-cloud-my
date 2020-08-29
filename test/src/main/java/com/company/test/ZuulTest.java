package com.company.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Slf4j
public class ZuulTest {

	public static void main(String[] args) throws InterruptedException {

		Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http"));
		for (String log : loggers) {
			Logger logger = (Logger) LoggerFactory.getLogger(log);
			logger.setLevel(Level.INFO);
			logger.setAdditive(false);
		}

//		test1();
		for (int i = 0; i < 1; i++) {
			test2();
		}
	}

	private static void test2() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				10,
				10,
				10000,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory()
		, new RejectedExecutionHandler() {
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
					HttpPost httpPost = new HttpPost("http://localhost:9096/inner/home?token=123");
					CloseableHttpClient build = HttpClientBuilder.create().build();
					CloseableHttpResponse execute = build.execute(httpPost);
					String s = EntityUtils.toString(execute.getEntity(), "utf-8");
					System.out.println(s);
				}
			});
		}
		Thread.sleep(3000);
		countDownLatch.countDown();
		threadPoolExecutor.shutdown();
	}

	private static void test1() throws InterruptedException {
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
		threadPoolExecutor.shutdown();
	}
}
