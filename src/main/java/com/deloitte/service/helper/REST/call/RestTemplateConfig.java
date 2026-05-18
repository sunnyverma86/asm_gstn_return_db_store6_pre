package com.deloitte.service.helper.REST.call;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	RestTemplate restTemplate() {

		// Connection Manager
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

		connectionManager.setMaxTotal(50);
		connectionManager.setDefaultMaxPerRoute(10);

		// Timeout Configuration
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofSeconds(10)) // Connection
																										// timeout
				.setResponseTimeout(Timeout.ofSeconds(30)) // Read timeout
				.build();

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
				.setDefaultRequestConfig(requestConfig).evictExpiredConnections().build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return new RestTemplate(requestFactory);
	}
}
