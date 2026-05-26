package com.deloitte.service.utility;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.deloitte.returns.entity.Forest.Model.ForestApiResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ForestApiClient {

	private static final String URL = "https://assamforestonline.in/api/ForestGstItReport";

	private static final String USERNAME = "Forest@123#";
	private static final String PASSWORD = "df6fb2818447541c71dafd9a5a71f8f6ffedfbccb3f39436f6e4827a30072093";

	@Autowired
	private RestTemplate restTemplate;

	public ForestApiResponse fetchMonthlyData(LocalDate fromDate, LocalDate toDate, int page, int perPage,
			int maxRetry) {

		int attempt = 1;

		while (attempt <= maxRetry) {

			try {
				return fetchData(fromDate, toDate, page, perPage);

			} catch (Exception ex) {
				log.error("API_FAIL {} to {} page {} err={}", fromDate, toDate, page, ex.getMessage());
			}

			attempt++;
			sleep(3000);
		}
		return null;
	}

	private ForestApiResponse fetchData(LocalDate fromDate, LocalDate toDate, int page, int perPage) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBasicAuth(USERNAME, PASSWORD);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("from_date", fromDate.toString());
		body.add("to_date", toDate.toString());
		body.add("page", page);
		body.add("per_page", perPage);
		body.add("action", "dsfsd");

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

		return restTemplate.exchange(URL, HttpMethod.POST, request, ForestApiResponse.class).getBody();
	}

	private ForestApiResponse fetchDataOld(LocalDate fromDate, LocalDate toDate, int page, int perPage) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBasicAuth(USERNAME, PASSWORD);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("from_date", fromDate.toString());
		body.add("to_date", toDate.toString());
		body.add("page", page);
		body.add("per_page", perPage);
		body.add("action", "dsfsd");

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

		return restTemplate.exchange(URL, HttpMethod.POST, request, ForestApiResponse.class).getBody();
	}

	public ForestApiResponse fetchWithRetry(LocalDate from, LocalDate to, int page, int maxRetry, int timeoutMinutes) {

		int attempt = 1;

		while (attempt <= maxRetry) {
			try {
				return fetchData(from, to, page);
			} catch (Exception ex) {
				log.error("API error page {} | {} - {} : {}", page, from, to, ex.getMessage());
				attempt++;
				sleep(5000);
			}
		}
		return null;
	}

	public ForestApiResponse fetchData(LocalDate from, LocalDate to, int page) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBasicAuth(USERNAME, PASSWORD);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("from_date", from.toString());
		body.add("to_date", to.toString());
		body.add("page", page);
		body.add("per_page", 100);
		body.add("action", "fetchGstItReport");

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

		return restTemplate.exchange(URL, HttpMethod.POST, request, ForestApiResponse.class).getBody();
	}

	private void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ignored) {
		}
	}

}
