package com.deloitte.service.helper.REST.call;


import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientHelper {
	private static final Logger logger = LoggerFactory.getLogger(RestClientHelper.class);

	private final RestTemplate restTemplate;

	public RestClientHelper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Async
	public <T> CompletableFuture<T> getAsync(String url, Class<T> responseType, HttpHeaders headers) {
		return CompletableFuture.supplyAsync(() -> get(url, responseType, headers));
	}

	@Async
	public <T> CompletableFuture<T> postAsync(String url, Class<T> responseType, HttpHeaders headers,
			String requestBody) {
		return CompletableFuture.supplyAsync(() -> post(url, responseType, headers, requestBody));
	}

	public <T> T get(String url, Class<T> responseType, HttpHeaders headers) {
		Boolean retry = false;
		ResponseEntity<T> responseEntity = null;
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		do {
			if (retry) {
				logger.info("Re-Trying GET call due to conenction issue");
			}
			try {

				retry = false;
				responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
			} catch (ResourceAccessException e) {
				retry = true;
				logger.error("Exception while Processesing GET Call " + e.getMessage());

			} catch (Exception e) {
				if (e.getMessage().contains("Connection timed out: connect")) {
					retry = true;
				} else {
					logger.error("Exception while Processesing GET Call " + e.getMessage());
				}
			}
		} while (retry);
		return responseEntity.getBody();
	}

	public <T> T post(String url, Class<T> responseType, HttpHeaders headers, String requestBody) {
		Boolean retry = false;
		ResponseEntity<T> responseEntity = null;
		HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
		do {
			if (retry) {
				logger.info("Re-Trying POST call due to conenction issue");
			}
			try {

				retry = false;
				responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);

			} catch (ResourceAccessException e) {
				retry = true;
				logger.error("Exception while Processesing POST Call " + e.getMessage());

			} catch (Exception e) {
				if (e.getMessage().contains("Connection timed out: connect")) {
					retry = true;
				} else {
					logger.error("Exception while Processesing POST Call " + e.getMessage());
				}
			}
		} while (retry);
		return responseEntity.getBody();
	}

	// You can add other HTTP methods like PUT, DELETE if needed
}