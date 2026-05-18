package com.deloitte.returns.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.MasterData;

@Component
public class AuthenticationHelper {

	public HttpHeaders getHeaders(HttpHeaders headers, Map<String, String> inputMap) {
		for (Map.Entry<String, String> data : inputMap.entrySet())
			headers.set(data.getKey(), data.getValue());
		return headers;
	}

	public HttpHeaders getDefaultHeaders(MasterData masterData) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("clientid", masterData.getClientId());
		headers.set("client-secret", masterData.getClientSecret());
		headers.set("username", masterData.getUserName());
		headers.set("state-cd", masterData.getStateCd());
		return headers;
	}

	public HttpHeaders getDefaultHeadersEnforcement(MasterData masterData, String authToken, String apiContentType) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("clientid", masterData.getClientId());
		headers.set("client-secret", masterData.getClientSecret());
		headers.set("username", masterData.getUserName());
		headers.set("state-cd", masterData.getStateCd());
		headers.set("auth-token", authToken);
		headers.set("content-type", apiContentType);
		headers.set("taxoffnam", "ANJALI SINGH");
		headers.set("taxoffjud", "AS010");
		headers.set("taxoffid", "ANJALI.SINGH01");
		return headers;
	}

	public String getUriWithParam(String path, Map<String, String> params) {

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(path);

		for (Map.Entry<String, String> entry : params.entrySet()) {
			uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
		}

		return uriComponentsBuilder.build().toUriString();
	}

	public String getFullPath(MasterData masterData, APIDetails apiDetails) {
		return masterData.getHostName().concat(apiDetails.getApiPath());
	}

	public String getApiPath(APIDetails apiDetails) {
		return apiDetails.getApiPath();
	}

	public HttpHeaders getDefaultHeaders(MasterData masterData, String authToken, String apiContentType) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("clientid", masterData.getClientId());
		headers.set("client-secret", masterData.getClientSecret());
		headers.set("username", masterData.getUserName());
		headers.set("state-cd", masterData.getStateCd());
		headers.set("auth-token", authToken);
		headers.set("content-type", apiContentType);
		return headers;
	}

}
