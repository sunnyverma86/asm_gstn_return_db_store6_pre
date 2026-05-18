package com.deloitte.service.helper.REST.call;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(5); // was 50 ❌
		executor.setMaxPoolSize(10); // was 200 ❌
		executor.setQueueCapacity(100); // was 500 ❌

		executor.setThreadNamePrefix("RestClient-");
		executor.initialize();

		return executor;
	}

}
