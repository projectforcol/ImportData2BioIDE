package org.big.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * @EnableAsync 利用EnableAsync来开启Springboot对于异步任务的支持
 * @Description 配置类实现接口AsyncConfigurator，返回一个ThreadPoolTaskExecutor线程池对象。
 * @Description 异步方法和调用方法一定要写在“不同的类”中如果写在一个类中，是没有效果的
 * @Description 异步方法使用注解@Async ,返回值为void或者Future
 * @author ZXY
 */
@Configuration
@EnableAsync//利用EnableAsync来开启Springboot对于异步任务的支持
public class ThreadConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(25);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}