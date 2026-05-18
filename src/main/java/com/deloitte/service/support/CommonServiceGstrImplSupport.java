package com.deloitte.service.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.BaseJsonEntity;
import com.deloitte.returns.entity.Cmp08InitialJson;
import com.deloitte.returns.entity.Itc02InitialJson;
import com.deloitte.returns.entity.PaymentInitialJson;
import com.deloitte.returns.entity.R10InitialJson;
import com.deloitte.returns.entity.R11InitialJson;
import com.deloitte.returns.entity.R1InitialJson;
import com.deloitte.returns.entity.R1aInitialJson;
import com.deloitte.returns.entity.R2bInitialJson;
import com.deloitte.returns.entity.R3bInitialJson;
import com.deloitte.returns.entity.R4InitialJson;
import com.deloitte.returns.entity.R5InitialJson;
import com.deloitte.returns.entity.R6InitialJson;
import com.deloitte.returns.entity.R7InitialJson;
import com.deloitte.returns.entity.R8InitialJson;
import com.deloitte.returns.entity.R98aInitialJson;
import com.deloitte.returns.entity.R9InitialJson;
import com.deloitte.returns.entity.R9aInitialJson;
import com.deloitte.returns.entity.R9cInitialJson;
import com.deloitte.returns.entity.ReturnGzJsonStorage;
import com.deloitte.service.impl.APIDetailsImpl;

@Component
public class CommonServiceGstrImplSupport {

	@Autowired
	protected APIDetailsImpl apiDetailsImpl;

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

	public Map<String, String> getParamsForGetReturnFileCount(APIDetails apiDetails, MasterData masterData, String date,
			String application) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", apiDetails.getApiAction());
		params.put("state_cd", masterData.getStateCd());
		params.put("date", date);
		if (application.equalsIgnoreCase("cmp08")) {
			params.put("type", "CM8");
		} else if (application.equalsIgnoreCase("payment")) { // new add
			params.put("file_type", "EODCIN"); // new add
		} else if (application.equalsIgnoreCase("gstr1")) {
			params.put("type", "R1");
		} else if (application.equalsIgnoreCase("gstr1a")) {
			params.put("type", "R1A");
		} else if (application.equalsIgnoreCase("gstr2b")) {
			params.put("type", "R2B");
		} else if (application.equalsIgnoreCase("gstr3b")) {
			params.put("type", "R3B");
		} else if (application.equalsIgnoreCase("gstr4")) {
			params.put("type", "R4");
		} else if (application.equalsIgnoreCase("gstr5")) {
			params.put("type", "R5");
		} else if (application.equalsIgnoreCase("gstr6")) {// new add
			params.put("type", "R6");
		} else if (application.equalsIgnoreCase("gstr7")) {
			params.put("type", "R7");
		} else if (application.equalsIgnoreCase("gstr8")) {
			params.put("type", "R8");
		} else if (application.equalsIgnoreCase("gstr9")) {
			params.put("type", "R9");
		} else if (application.equalsIgnoreCase("gstr9a")) {
			params.put("type", "R9A");
		} else if (application.equalsIgnoreCase("gstr98a")) {
			params.put("type", "R98A");
		} else if (application.equalsIgnoreCase("gstr9c")) {
			params.put("type", "R9C");
		} else if (application.equalsIgnoreCase("gstr10")) {
			params.put("type", "R10");
		} else if (application.equalsIgnoreCase("gstr11")) {
			params.put("type", "R11");
		} else if (application.equalsIgnoreCase("gstr12")) {
			params.put("type", "R12");
		} else if (application.equalsIgnoreCase("gstr13")) {
			params.put("type", "R13");
		} else if (application.equalsIgnoreCase("gstr14")) {
			params.put("type", "R14");
		} else if (application.equalsIgnoreCase("gstr3")) {
			params.put("type", "R3");
		} else if (application.equalsIgnoreCase("gstr1r3b")) {
			params.put("type", "R1R3B");

		} else if (application.equalsIgnoreCase("itc02")) {
			params.put("type", "ITC02");
		} else if (application.equalsIgnoreCase("pmt")) {
			params.put("type", "PMT");
		} else if (application.equalsIgnoreCase("R1")) {
			params.put("type", "R1");
		} else if (application.equalsIgnoreCase("R1A")) {
			params.put("type", "R1A");
		} else if (application.equalsIgnoreCase("R2B")) {
			params.put("type", "R2B");
		} else if (application.equalsIgnoreCase("R3B")) {
			params.put("type", "R3B");
		} else if (application.equalsIgnoreCase("R4")) {
			params.put("type", "R4");
		} else if (application.equalsIgnoreCase("R5")) {
			params.put("type", "R5");
		} else if (application.equalsIgnoreCase("R6")) {// new add
			params.put("type", "R6");
		} else if (application.equalsIgnoreCase("R7")) {
			params.put("type", "R7");
		} else if (application.equalsIgnoreCase("R8")) {
			params.put("type", "R8");
		} else if (application.equalsIgnoreCase("R9")) {
			params.put("type", "R9");
		} else if (application.equalsIgnoreCase("R9A")) {
			params.put("type", "R9A");
		} else if (application.equalsIgnoreCase("R98A")) {
			params.put("type", "R98A");
		} else if (application.equalsIgnoreCase("R9C")) {
			params.put("type", "R9C");
		} else if (application.equalsIgnoreCase("R10")) {
			params.put("type", "R10");
		} else if (application.equalsIgnoreCase("R11")) {
			params.put("type", "R11");
		} else if (application.equalsIgnoreCase("R12")) {
			params.put("type", "R12");
		} else if (application.equalsIgnoreCase("gstr13")) {
			params.put("type", "R13");
		} else if (application.equalsIgnoreCase("gstr14")) {
			params.put("type", "R14");
		} else if (application.equalsIgnoreCase("R3")) {
			params.put("type", "R3");
		} else if (application.equalsIgnoreCase("R1R3B")) {
			params.put("type", "R1R3B");

		} else if (application.equalsIgnoreCase("ITC02")) {
			params.put("type", "ITC02");
		} else if (application.equalsIgnoreCase("PMT")) {
			params.put("type", "PMT");
		} else {
			params.put("type", "--");
		}

		return params;
	}

	public Map<String, String> getParamsForGetReturnFileDetails(APIDetails apiDetails, MasterData masterData,
			String date, String application) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", apiDetails.getApiAction());
		params.put("state_cd", masterData.getStateCd());
		params.put("date", date);
		if (application.equalsIgnoreCase("cmp08")) {
			params.put("type", "CM8");
		} else if (application.equalsIgnoreCase("gstr1a")) {
			params.put("type", "R1A");
		} else if (application.equalsIgnoreCase("gstr1")) {
			params.put("type", "R1");
		} else if (application.equalsIgnoreCase("gstr2b")) {
			params.put("type", "R2B");
		} else if (application.equalsIgnoreCase("gstr3b")) {
			params.put("type", "R3B");
		} else if (application.equalsIgnoreCase("gstr4")) {
			params.put("type", "R4");
		} else if (application.equalsIgnoreCase("gstr5")) {
			params.put("type", "R5");
		} else if (application.equalsIgnoreCase("gstr6")) {
			params.put("type", "R6");
		} else if (application.equalsIgnoreCase("gstr7")) {
			params.put("type", "R7");
		} else if (application.equalsIgnoreCase("gstr8")) {
			params.put("type", "R8");
		} else if (application.equalsIgnoreCase("gstr9")) {
			params.put("type", "R9");
		} else if (application.equalsIgnoreCase("gstr9a")) {
			params.put("type", "R9A");
		} else if (application.equalsIgnoreCase("gstr98a")) {
			params.put("type", "R98A");
		} else if (application.equalsIgnoreCase("gstr9c")) {
			params.put("type", "R9C");
		} else if (application.equalsIgnoreCase("gstr10")) {
			params.put("type", "R10");
		} else if (application.equalsIgnoreCase("itc02")) {
			params.put("type", "ITC02");
		} else if (application.equalsIgnoreCase("pmt")) {
			params.put("type", "PMT");
		} else if (application.equalsIgnoreCase("gstr3")) {
			params.put("type", "R3");
		} else if (application.equalsIgnoreCase("gstr11")) {
			params.put("type", "R11");
		} else if (application.equalsIgnoreCase("gstr12")) {
			params.put("type", "R12");
		} else if (application.equalsIgnoreCase("gstr13")) {
			params.put("type", "R13");
		} else if (application.equalsIgnoreCase("gstr14")) {
			params.put("type", "R14");
		} else if (application.equalsIgnoreCase("gstr1r3b")) {
			params.put("type", "R1R3B");
		} else if (application.equalsIgnoreCase("payment")) { // new add
			params.put("action", "FILEDTLS");
			params.put("file_type", "EODCIN"); // new add
		} else if (application.equalsIgnoreCase("R1A")) {
			params.put("type", "R1A");
		} else if (application.equalsIgnoreCase("R1")) {
			params.put("type", "R1");
		} else if (application.equalsIgnoreCase("R2B")) {
			params.put("type", "R2B");
		} else if (application.equalsIgnoreCase("R3B")) {
			params.put("type", "R3B");
		} else if (application.equalsIgnoreCase("R4")) {
			params.put("type", "R4");
		} else if (application.equalsIgnoreCase("R5")) {
			params.put("type", "R5");
		} else if (application.equalsIgnoreCase("R6")) {
			params.put("type", "R6");
		} else if (application.equalsIgnoreCase("R7")) {
			params.put("type", "R7");
		} else if (application.equalsIgnoreCase("R8")) {
			params.put("type", "R8");
		} else if (application.equalsIgnoreCase("R9")) {
			params.put("type", "R9");
		} else if (application.equalsIgnoreCase("R9A")) {
			params.put("type", "R9A");
		} else if (application.equalsIgnoreCase("R98A")) {
			params.put("type", "R98A");
		} else if (application.equalsIgnoreCase("R9C")) {
			params.put("type", "R9C");
		} else if (application.equalsIgnoreCase("R10")) {
			params.put("type", "R10");
		} else if (application.equalsIgnoreCase("ITC02")) {
			params.put("type", "ITC02");
		} else if (application.equalsIgnoreCase("PMT")) {
			params.put("type", "PMT");
		} else if (application.equalsIgnoreCase("R3")) {
			params.put("type", "R3");
		} else if (application.equalsIgnoreCase("R11")) {
			params.put("type", "R11");
		} else if (application.equalsIgnoreCase("R12")) {
			params.put("type", "R12");
		} else {
			params.put("type", "--");
		}
		return params;
	}

	public String normalizeApplication(String application) {
		if (application == null) {
			return null;
		}

		Map<String, String> appMapping = new HashMap<>();
		appMapping.put("cmp08", "CM8");
		appMapping.put("gstr1", "R1");
		appMapping.put("gstr1a", "R1A");
		appMapping.put("gstr2b", "R2B");
		appMapping.put("gstr3b", "R3B");
		appMapping.put("gstr4", "R4");
		appMapping.put("gstr5", "R5");
		appMapping.put("gstr6", "R6");
		appMapping.put("gstr7", "R7");
		appMapping.put("gstr8", "R8");
		appMapping.put("gstr9", "R9");
		appMapping.put("gstr98a", "R98A");
		appMapping.put("gstr9c", "R9C");
		appMapping.put("gstr9a", "R9A");
		appMapping.put("gstr10", "R10");
		appMapping.put("gstr11", "R11");
		// appMapping.put("payment", "EODCIN");

		appMapping.put("CM8", "CM8");
		appMapping.put("R1", "R1");
		appMapping.put("R1A", "R1A");
		appMapping.put("R2B", "R2B");
		appMapping.put("R3B", "R3B");
		appMapping.put("R4", "R4");
		appMapping.put("R5", "R5");
		appMapping.put("R6", "R6");
		appMapping.put("R7", "R7");
		appMapping.put("R8", "R8");
		appMapping.put("R9", "R9");
		appMapping.put("R98A", "R98A");
		appMapping.put("R9C", "R9C");
		appMapping.put("R9A", "R9A");
		appMapping.put("R10", "R10");
		appMapping.put("R11", "R11");
		appMapping.put("payment", "payment");

		return appMapping.getOrDefault(application.toLowerCase(), application);
	}

	public String extractErrorMessage(GSTCommonResponseBean response) {

		if (response == null) {
			return "Null response received from GST server";
		}

		if (response.getError() != null && response.getError().get("message") != null) {
			return response.getError().get("message");
		}

		return response.toString();
	}

	public APIDetails resolveApiDetails(String application) {// count

		if ("gstr2b".equalsIgnoreCase(application) || "R2B".equalsIgnoreCase(application)) {
			return apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_COUNT_GSTR2B);

		} else if ("payment".equalsIgnoreCase(application)) {
			return apiDetailsImpl.findByName(Constants.GET_PAYMENT_FILE_COUNT);

		} else {
			return apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_COUNT);
		}
	}

	public APIDetails resolveFileDetailsApi(String application) {// detail

		if ("gstr2b".equalsIgnoreCase(application) || "R2B".equalsIgnoreCase(application)) {
			return apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL_GSTR2B);

		} else if ("payment".equalsIgnoreCase(application)) {
			return apiDetailsImpl.findByName(Constants.GET_PAYMENT_FILE_DETAIL);

		} else {
			return apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL);
		}
	}

	public BaseJsonEntity getEntityByApplication(String application) {

		switch (application.toUpperCase()) {

		case "PAYMENT":
			return new PaymentInitialJson();

		case "CMP08":
			return new Cmp08InitialJson();

		case "GSTR1":
			return new R1InitialJson();

		case "GSTR1A":
			return new R1aInitialJson();

		case "GSTR2B":
			return new R2bInitialJson();

		case "GSTR3B":
			return new R3bInitialJson();

		case "GSTR4":
			return new R4InitialJson();

		case "GSTR5":
			return new R5InitialJson();

		case "GSTR6":
			return new R6InitialJson();

		case "GSTR7":
			return new R7InitialJson();

		case "GSTR8":
			return new R8InitialJson();

		case "GSTR9":
			return new R9InitialJson();

		case "GSTR98A":
			return new R98aInitialJson();

		case "GSTR9C":
			return new R9cInitialJson();

		case "GSTR9A":
			return new R9aInitialJson();

		case "GSTR10":
			return new R10InitialJson();

		case "GSTR11":
			return new R11InitialJson();

		case "ITC02":
			return new Itc02InitialJson();

		case "CM8":
			return new Cmp08InitialJson();

		case "R1":
			return new R1InitialJson();

		case "R1A":
			return new R1aInitialJson();

		case "R2B":
			return new R2bInitialJson();

		case "R3B":
			return new R3bInitialJson();

		case "R4":
			return new R4InitialJson();

		case "R5":
			return new R5InitialJson();

		case "R6":
			return new R6InitialJson();

		case "R7":
			return new R7InitialJson();

		case "R8":
			return new R8InitialJson();

		case "R9":
			return new R9InitialJson();

		case "R98A":
			return new R98aInitialJson();

		case "R9C":
			return new R9cInitialJson();

		case "R9A":
			return new R9aInitialJson();

		case "R10":
			return new R10InitialJson();

		case "R11":
			return new R11InitialJson();

		case "ITCO2":
			return new Itc02InitialJson();

		default:
			return new ReturnGzJsonStorage();
		}
	}

}
