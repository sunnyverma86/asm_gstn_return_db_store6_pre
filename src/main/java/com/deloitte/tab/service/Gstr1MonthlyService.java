package com.deloitte.tab.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.tab.bean.Gstr1B2BDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Gstr1MonthlyService {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private GstUserSessionServices gstUserSessionServices;

	@Autowired
	private APIDetailsImpl apiDetailsImpl;

	@Autowired
	private RestClientHelper restClient;

	@Autowired
	private AuthenticationHelper authenticationHelper;

	public List<Gstr1B2BDto> getGstr1SummaryReportSections(String userName, String gstin, String fy, String sections) {

		long startTime = System.currentTimeMillis();

		log.info("Starting GSTR1 Summary Report generation. User={}, GSTIN={}, FY={}, Section={}", userName, gstin, fy,
				sections);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);

		if (masterData == null) {
			log.error("Master data not found for user={}", userName);
			throw new RuntimeException("Master Data Not Found");
		}

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);

		if (session == null) {
			log.error("GST session not found for user={}", userName);
			throw new RuntimeException("GST Session Not Found");
		}

		List<String> periods = generateReturnPeriods(fy);

		log.info("Generated {} return periods for FY={}", periods.size(), fy);

		List<Gstr1B2BDto> result = new ArrayList<>();

		for (String retPeriod : periods) {

			long periodStart = System.currentTimeMillis();

			log.info("Processing Return Period={} GSTIN={} Section={}", retPeriod, gstin, sections);

			try {

				String response = callGstr1Api(gstin, retPeriod, masterData, session, sections);

				JsonNode root = mapper.readTree(response);

				String statusCd = root.path("status_cd").asText();

				if (!"1".equals(statusCd)) {

					log.warn("No data received. GSTIN={}, ReturnPeriod={}, StatusCode={}", gstin, retPeriod, statusCd);

					continue;
				}

				String encodedData = root.path("data").asText();

				if (encodedData == null || encodedData.isBlank()) {

					log.warn("Empty data received. GSTIN={}, ReturnPeriod={}", gstin, retPeriod);

					continue;
				}

				String decoded = new String(Base64.getDecoder().decode(encodedData));

				JsonNode json = mapper.readTree(decoded);

				int beforeCount = result.size();

				List<Gstr1B2BDto> periodDtos = convertToDtos(json, gstin, retPeriod);
				result.addAll(periodDtos);
				int processed = result.size() - beforeCount;

				log.info("Successfully processed ReturnPeriod={} Records={} TimeTaken={} ms", retPeriod, processed,
						(System.currentTimeMillis() - periodStart));

			} catch (Exception ex) {

				log.error("Error while processing GSTIN={}, ReturnPeriod={}, Section={}. Error={}", gstin, retPeriod,
						sections, ex.getMessage(), ex);

			}
		}

		log.info("Completed GSTR1 Summary Report. GSTIN={}, FY={}, Section={}, TotalRecords={}, TotalTime={} ms", gstin,
				fy, sections, result.size(), (System.currentTimeMillis() - startTime));

		return result;
	}

	private String callGstr1Api(String gstin, String retPeriod, MasterData masterData, GSTUserSession session,
			String sections) throws Exception {

		Map<String, String> params = new HashMap<>();

		params.put("action", "ENFR1DET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		params.put("sec_name", sections);
		APIDetails apiDetails = apiDetailsImpl.findByName("ENFR3BDET");

		if (apiDetails == null) {

			throw new RuntimeException("API Details not configured");
		}

		HttpHeaders headers = getDefaultHeaders(masterData, session.getAuthToken());

		String apiPath = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
				params);

		GSTCommonResponseBean responseBean = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

		return mapper.writeValueAsString(responseBean);
	}

	public HttpHeaders getDefaultHeaders(MasterData masterData, String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("clientid", masterData.getClientId());
		headers.set("client-secret", masterData.getClientSecret());
		headers.set("username", masterData.getUserName());
		headers.set("state-cd", masterData.getStateCd());
		headers.set("auth-token", authToken);
		headers.set("content-type", "application/json");
		headers.set("taxoffnam", "ANJALI SINGH");
		headers.set("taxoffjud", "AS010");
		headers.set("taxoffid", "ANJALI.SINGH01");
		return headers;
	}

	private List<String> generateReturnPeriods(String fy) {

		String[] years = fy.split("-");

		int startYear = Integer.parseInt(years[0]);

		int endYear;

		if (years[1].length() == 2) {

			endYear = Integer.parseInt(String.valueOf(startYear).substring(0, 2) + years[1]);
		} else {

			endYear = Integer.parseInt(years[1]);
		}

		List<String> periods = new ArrayList<>();

		for (int month = 4; month <= 12; month++) {

			periods.add(String.format("%02d%d", month, startYear));
		}

		for (int month = 1; month <= 3; month++) {

			periods.add(String.format("%02d%d", month, endYear));
		}

		return periods;
	}

	private List<Gstr1B2BDto> convertToDtos(JsonNode json, String gstin, String returnPeriod) {

		long startTime = System.currentTimeMillis();

		log.debug("Converting B2B data to DTOs. GSTIN={}, ReturnPeriod={}", gstin, returnPeriod);

		List<Gstr1B2BDto> result = new ArrayList<>();

		JsonNode b2bArray = json.path("b2b");

		if (!b2bArray.isArray()) {
			log.warn("B2B array not found or invalid. GSTIN={}, ReturnPeriod={}", gstin, returnPeriod);
			return result;
		}

		log.debug("Found {} B2B buyers. GSTIN={}, ReturnPeriod={}", b2bArray.size(), gstin, returnPeriod);

		for (JsonNode buyerNode : b2bArray) {

			try {

				String purchaserGstin = buyerNode.path("ctin").asText();

				JsonNode invoices = buyerNode.path("inv");

				if (!invoices.isArray()) {
					log.warn("Invoice array missing for PurchaserGSTIN={}, ReturnPeriod={}", purchaserGstin,
							returnPeriod);
					continue;
				}

				for (JsonNode invoice : invoices) {

					try {

						String invoiceNo = invoice.path("inum").asText();
						String invoiceDate = invoice.path("idt").asText();

						BigDecimal invoiceValue = BigDecimal.valueOf(invoice.path("val").asDouble());

						String reverseCharge = invoice.path("rchrg").asText();

						JsonNode items = invoice.path("itms");

						if (!items.isArray()) {
							log.warn("Items array missing. InvoiceNo={}, PurchaserGSTIN={}", invoiceNo, purchaserGstin);
							continue;
						}

						for (JsonNode item : items) {

							JsonNode det = item.path("itm_det");

							Gstr1B2BDto dto = new Gstr1B2BDto();

							dto.setGstin(gstin);
							dto.setReturnPeriod(formatMonth(returnPeriod));
							dto.setPurchaserGstin(purchaserGstin);
							dto.setInvoiceNo(invoiceNo);
							dto.setInvoiceDate(invoiceDate);
							dto.setInvoiceValue(invoiceValue);
							dto.setRate(BigDecimal.valueOf(det.path("rt").asDouble()));
							dto.setTaxableValue(BigDecimal.valueOf(det.path("txval").asDouble()));
							dto.setIgst(BigDecimal.valueOf(det.path("iamt").asDouble()));
							dto.setCgst(BigDecimal.valueOf(det.path("camt").asDouble()));
							dto.setSgst(BigDecimal.valueOf(det.path("samt").asDouble()));
							dto.setCess(BigDecimal.valueOf(det.path("csamt").asDouble()));
							dto.setReverseCharge(reverseCharge);

							result.add(dto);
						}

					} catch (Exception ex) {

						log.error(
								"Error processing invoice. GSTIN={}, ReturnPeriod={}, PurchaserGSTIN={}, InvoiceNo={}, Error={}",
								gstin, returnPeriod, purchaserGstin, invoice.path("inum").asText(), ex.getMessage(),
								ex);
					}
				}

			} catch (Exception ex) {

				log.error("Error processing B2B buyer. GSTIN={}, ReturnPeriod={}, PurchaserGSTIN={}, Error={}", gstin,
						returnPeriod, buyerNode.path("ctin").asText(), ex.getMessage(), ex);
			}
		}

		log.info("B2B conversion completed. GSTIN={}, ReturnPeriod={}, Records={}, TimeTaken={} ms", gstin,
				returnPeriod, result.size(), System.currentTimeMillis() - startTime);

		return result;
	}

	private String formatMonth(String returnPeriod) {

		if (returnPeriod == null || returnPeriod.length() != 6) {
			return returnPeriod;
		}

		int month = Integer.parseInt(returnPeriod.substring(0, 2));
		String year = returnPeriod.substring(2);

		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		return months[month - 1] + "-" + year;
	}

}
