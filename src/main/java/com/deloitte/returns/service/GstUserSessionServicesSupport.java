package com.deloitte.returns.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.deloitte.common.bean.GSTAuthenticationInputBean;
import com.deloitte.common.bean.GSTAuthenticationResponseBean;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.MasterData;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.ObjectMapper;

@Service
@Log4j2
public class GstUserSessionServicesSupport {

	@Autowired
	private AuthenticationHelper authenticationHelper;

	@Autowired
	private RestClientHelper restClient;

	public GSTAuthenticationResponseBean doAuth(APIDetails apiDetails,
			GSTAuthenticationInputBean gstAuthenticationInputBean, MasterData masterData) {
		GSTAuthenticationResponseBean responseEntity = null;
		try {
			HttpHeaders headersForAuthentication = authenticationHelper.getDefaultHeaders(masterData);
			headersForAuthentication.set("content-type", apiDetails.getApiContentType());
			responseEntity = restClient.post(authenticationHelper.getFullPath(masterData, apiDetails),
					GSTAuthenticationResponseBean.class, headersForAuthentication,
					getRequestBody(gstAuthenticationInputBean));

		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerEx) {
			log.error("Exception while authenticating user session " + httpClientOrServerEx.toString());
			// throw httpClientOrServerEx;
		} catch (Exception ex) {
			log.error("Exception while authenticating user session " + ex.toString());
			// throw ex;
		}
		return responseEntity;
	}

	private String getRequestBody(GSTAuthenticationInputBean gstAuthenticationInputBean) throws JsonProcessingException {
		Map<String, String> requestBodyMap = new HashMap<>();
		requestBodyMap.put("action", gstAuthenticationInputBean.getAction());
		requestBodyMap.put("username", gstAuthenticationInputBean.getUsername());
		requestBodyMap.put("password", gstAuthenticationInputBean.getPassword());
		requestBodyMap.put("app_key", gstAuthenticationInputBean.getAppKey());
		return new ObjectMapper().writeValueAsString(requestBodyMap);
	}

}
