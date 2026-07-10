package com.deloitte.tab.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.tab.bean.Gstr3BMonthlyDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Gstr3BMonthlyService {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private GstUserSessionServices gstUserSessionServices;

	@Autowired
	protected APIDetailsImpl apiDetailsImpl;

	@Autowired
	private RestClientHelper restClient;

	@Autowired
	private AuthenticationHelper authenticationHelper;

//	public List<Gstr3BMonthlyDto> getGstr3BSummary(String userName, String gstin, String fy) throws Exception {
//
//		long startTime = System.currentTimeMillis();
//
//		MasterData masterData = masterDataService.getMasterdatabyName(userName);
//
//		if (masterData == null) {
//			throw new RuntimeException("Master data not found");
//		}
//
//		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);
//
//		if (session == null) {
//			throw new RuntimeException("Session not authenticated");
//		}
//
//		String stateCode = gstin.substring(0, 2);
//
//		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//
//		int fileCount = getFileCount(stateCode, currentDate, masterData, session);
//
//		List<String> periods = generateTaxPeriods(fy);
//
//		List<Gstr3BMonthlyDto> result = new ArrayList<>();
//
//		for (int fileNum = fileCount; fileNum >= 1; fileNum--) {
//
//			String downloadUrl = getDownloadUrl(stateCode, currentDate, fileNum, masterData, session);
//
//			if (downloadUrl == null) {
//				continue;
//			}
//
//			File tarFile = downloadFile(downloadUrl);
//
//			String extractFolder = "D:/gstn/extract/" + UUID.randomUUID();
//
//			extractTarGz(tarFile, extractFolder);
//
//			for (String period : periods) {
//
//				File jsonFile = findJson(extractFolder, gstin, period);
//
//				if (jsonFile != null) {
//
//					result.add(convertToDto(jsonFile));
//				}
//			}
//		}
//
//		log.info("Completed in {} ms", System.currentTimeMillis() - startTime);
//
//		return result.stream().collect(Collectors.toMap(Gstr3BMonthlyDto::getMonth, Function.identity(), (a, b) -> a))
//				.values().stream().sorted(Comparator.comparing(Gstr3BMonthlyDto::getMonth))
//				.collect(Collectors.toList());
//	}

	public List<Gstr3BMonthlyDto> getGstr3BSummaryoLD(String userName, String gstin, String fy) throws Exception {

		long startTime = System.currentTimeMillis();

		log.info("Starting GSTR3B Summary for GSTIN={} FY={}", gstin, fy);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);

		if (masterData == null) {
			throw new RuntimeException("Master data not found");
		}

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);

		if (session == null) {
			throw new RuntimeException("Session not authenticated");
		}

		String stateCode = gstin.substring(0, 2);

		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		int fileCount = getFileCount(stateCode, currentDate, masterData, session);

		log.info("Total GSTN Files Available : {}", fileCount);

		List<String> periods = generateTaxPeriods(fy);

		List<Gstr3BMonthlyDto> result = new ArrayList<>();

		for (int fileNum = fileCount; fileNum >= 1; fileNum--) {

			try {

				log.info("Processing File Number : {}", fileNum);

				String downloadUrl = getDownloadUrl(stateCode, currentDate, fileNum, masterData, session);

				if (downloadUrl == null || downloadUrl.trim().isEmpty()) {

					log.warn("Download URL not found for file {}", fileNum);
					continue;
				}

				File tarFile = downloadFile(downloadUrl);

				if (tarFile == null || !tarFile.exists()) {

					log.warn("Downloaded file missing for file {}", fileNum);
					continue;
				}

				String extractFolder = "D:/gstn/extract/" + UUID.randomUUID();

				extractTarGz(tarFile, extractFolder);

				long jsonCount;

				try (Stream<Path> paths = Files.walk(Paths.get(extractFolder))) {

					jsonCount = paths.filter(Files::isRegularFile)
							.filter(p -> p.getFileName().toString().toLowerCase().endsWith(".json")).count();
				}

				log.info("File {} Extracted Successfully. Json Count={}", fileNum, jsonCount);

				for (String period : periods) {

					File jsonFile = findJson(extractFolder, gstin, period);

					if (jsonFile == null) {

						log.debug("Json Not Found : {}_{}.json", gstin, period);

						continue;
					}

					log.info("Json Found : {}", jsonFile.getAbsolutePath());

					Gstr3BMonthlyDto dto = convertToDto(jsonFile);

					result.add(dto);
				}

				try {
					Files.deleteIfExists(tarFile.toPath());
				} catch (Exception ex) {
					log.warn("Unable to delete temp tar file");
				}

			} catch (Exception ex) {

				log.error("Error while processing file {}", fileNum, ex);

			}
		}

		List<Gstr3BMonthlyDto> finalResult = result.stream()
				.collect(Collectors.toMap(Gstr3BMonthlyDto::getMonth, Function.identity(),
						(oldValue, newValue) -> newValue))
				.values().stream().sorted(Comparator.comparing(Gstr3BMonthlyDto::getMonth))
				.collect(Collectors.toList());

		log.info("Completed GSTR3B Summary. Records={} Time={} ms", finalResult.size(),
				(System.currentTimeMillis() - startTime));

		return finalResult;
	}

	private int getFileCount(String stateCode, String date, MasterData masterData, GSTUserSession session)
			throws Exception {

		// Replace with your existing GSTN API call

		String response = callGstnApi(date, "FILECNT", stateCode, null, masterData, session);

		JsonNode root = mapper.readTree(response);

		String decoded = new String(Base64.getDecoder().decode(root.path("data").asText()));

		JsonNode dataNode = mapper.readTree(decoded);

		return dataNode.path("num_files").asInt();
	}

	private String getDownloadUrl(String stateCode, String date, int fileNum, MasterData masterData,
			GSTUserSession session) throws Exception {

		String response = callGstnApi(date, "FILEDET", stateCode, fileNum, masterData, session);

		JsonNode root = mapper.readTree(response);

		String decoded = new String(Base64.getDecoder().decode(root.path("data").asText()));

		JsonNode data = mapper.readTree(decoded);

		return data.path("url").asText();
	}

//	private String callGstnApi(String date, String action, String stateCode, Integer fileNum, MasterData masterData,
//			GSTUserSession session) throws Exception {
//
//		Map<String, String> params = new HashMap<>();
//
//		params.put("action", action);
//		params.put("state_cd", stateCode);
//		params.put("date", date);
//		params.put("type", "R3B");
//
//		if ("FILEDET".equalsIgnoreCase(action)) {
//			params.put("file_num", String.valueOf(fileNum));
//		}
//
//		HttpHeaders headers = getDefaultHeaders(masterData, session.getAuthToken(), MediaType.APPLICATION_JSON_VALUE);
//
//		String apiPath = authenticationHelper.getUriWithParam("https://boapi.internal.gst.gov.in/govtapi/v1.0/returns",
//				params);
//
//		GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);
//
//		return mapper.writeValueAsString(response);
//	}

	private String callGstnApi(String date, String action, String stateCode, Integer fileNum, MasterData masterData,
			GSTUserSession session) throws Exception {

		Map<String, String> params = new HashMap<>();

		params.put("action", action);
		params.put("state_cd", stateCode);
		params.put("date", date);
		params.put("type", "R3B");

		APIDetails apiDetails;

		if ("FILECNT".equalsIgnoreCase(action)) {

			apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_COUNT);

		} else if ("FILEDET".equalsIgnoreCase(action)) {

			params.put("file_num", String.valueOf(fileNum));

			apiDetails = apiDetailsImpl.findByName(Constants.GET_RETURN_FILE_DETAIL);

		} else {

			throw new RuntimeException("Unsupported action : " + action);
		}

		if (apiDetails == null) {

			throw new RuntimeException("API Details not configured for action : " + action);
		}

		HttpHeaders headers = getDefaultHeaders(masterData, session.getAuthToken());

		String apiPath = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
				params);

		log.info("GSTN API URL : {}", apiPath);

		GSTCommonResponseBean responseBean = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

		if (responseBean == null) {

			throw new RuntimeException("Null response received from GSTN");
		}

		log.info("GSTN Response Status : {}", responseBean.getStatus_cd());

		if (responseBean.getStatus_cd() == null || !"1".equals(responseBean.getStatus_cd())) {

			throw new RuntimeException("GSTN API Failed : " + responseBean.getError());
		}

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

	public Map<String, String> getParamsForGetReturnFileCount(String date) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "FILECNT");
		params.put("date", date);
		params.put("type", "R3B");
		return params;
	}

	public Map<String, String> getParamsForGetReturnFileDetails(String date) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "FILEDET");
		params.put("date", date);
		params.put("type", "R3B");
		return params;
	}

	// downloadFile(...)
	// extractTarGz(...)
	// findJson(...)
	// generateTaxPeriods(...)
	// convertToDto(...)

	private List<String> generateTaxPeriods(String fy) {

		String startYear = fy.split("-")[0];

		int year = Integer.parseInt(startYear);

		List<String> periods = new ArrayList<>();

		for (int m = 4; m <= 12; m++) {
			periods.add(String.format("%02d%d", m, year));
		}

		for (int m = 1; m <= 3; m++) {
			periods.add(String.format("%02d%d", m, year + 1));
		}

		return periods;
	}

	private File downloadFile(String url) throws Exception {

		Path target = Paths.get("D:/gstn/temp/" + UUID.randomUUID() + ".tar.gz");

		Files.createDirectories(target.getParent());

		try (InputStream in = new URL(url).openStream()) {

			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		}

		return target.toFile();
	}

	private void extractTarGz(File tarGz, String outputDir) throws Exception {

		try (TarArchiveInputStream tis = new TarArchiveInputStream(
				new GzipCompressorInputStream(new FileInputStream(tarGz)))) {

			ArchiveEntry entry;

			while ((entry = tis.getNextEntry()) != null) {

				File output = new File(outputDir, entry.getName());

				if (entry.isDirectory()) {

					output.mkdirs();

				} else {

					output.getParentFile().mkdirs();

					Files.copy(tis, output.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	private File findJson(String folder, String gstin, String period) throws Exception {

		String expectedFile = gstin + "_" + period + ".json";

		log.info("Searching file : {}", expectedFile);

		Path root = Paths.get(folder);

		try (Stream<Path> paths = Files.walk(root)) {

			Optional<Path> file = paths.filter(Files::isRegularFile)
					.peek(p -> log.debug("Found File : {}", p.getFileName()))
					.filter(p -> p.getFileName().toString().equalsIgnoreCase(expectedFile)).findFirst();

			if (file.isPresent()) {
				log.info("JSON Found : {}", file.get());
			} else {
				log.warn("JSON NOT FOUND : {}", expectedFile);
			}

			return file.map(Path::toFile).orElse(null);
		}
	}

	private Gstr3BMonthlyDto convertToDto(File jsonFile) throws Exception {

		JsonNode json = mapper.readTree(jsonFile);

		Gstr3BMonthlyDto dto = new Gstr3BMonthlyDto();

		String retPeriod = json.path("ret_period").asText();

		dto.setMonth(formatMonth(retPeriod));

		dto.setFilingDate(json.path("fil_dt").asText());

		dto.setTurnover(json.at("/sup_details/osup_det/txval").decimalValue());

		BigDecimal taxLiability = json.at("/tx_pmt/net_tax_pay/0/cgst/tx").decimalValue()
				.add(json.at("/tx_pmt/net_tax_pay/0/sgst/tx").decimalValue())
				.add(json.at("/tx_pmt/net_tax_pay/0/igst/tx").decimalValue());

		dto.setTaxLiability(taxLiability);

		dto.setReverseCharge(json.at("/sup_details/isup_rev/txval").decimalValue());

		dto.setItcReverse(BigDecimal.ZERO);

		JsonNode cash = json.at("/tx_pmt/pdcash/0");

		if (!cash.isMissingNode()) {

			dto.setIgstCash(BigDecimal.valueOf(cash.path("ipd").asDouble()));

			dto.setCgstCash(BigDecimal.valueOf(cash.path("cpd").asDouble()));

			dto.setSgstCash(BigDecimal.valueOf(cash.path("spd").asDouble()));
		}

		BigDecimal interest = json.at("/intr_ltfee/intr_details/camt").decimalValue()
				.add(json.at("/intr_ltfee/intr_details/samt").decimalValue());

		dto.setInterest(interest);

		BigDecimal lateFee = json.at("/intr_ltfee/ltfee_details/camt").decimalValue()
				.add(json.at("/intr_ltfee/ltfee_details/samt").decimalValue());

		dto.setLateFee(lateFee);

		return dto;
	}

	private String formatMonth(String period) {

		int month = Integer.parseInt(period.substring(0, 2));

		int year = Integer.parseInt(period.substring(2));

		String[] months = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		return months[month] + "-" + year;
	}

	// NEW CHANGES
	public JsonNode getGstr3BDetail(String userName, String gstin, String retPeriod) throws Exception {

		MasterData masterData = masterDataService.getMasterdatabyName(userName);

		if (masterData == null) {
			throw new RuntimeException("Master data not found");
		}

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);

		if (session == null) {
			throw new RuntimeException("Session not authenticated");
		}

		Map<String, String> params = new HashMap<>();

		params.put("action", "ENFR3BDET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);

		APIDetails apiDetails = apiDetailsImpl.findByName("ENFR3BDET");

		if (apiDetails == null) {
			throw new RuntimeException("API Details not configured");
		}

		HttpHeaders headers = getDefaultHeaders(masterData, session.getAuthToken());
		headers.set("taxoffnam", "ANJALI SINGH");
		headers.set("taxoffjud", "AS010");
		headers.set("taxoffid", "ANJALI.SINGH01");

		String apiPath = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
				params);

		log.info("GSTN API URL : {}", apiPath);

		GSTCommonResponseBean responseBean = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

		if (responseBean == null) {
			throw new RuntimeException("Null response received from GSTN");
		}

		if (!"1".equals(responseBean.getStatus_cd())) {
			throw new RuntimeException("GSTN API Failed : " + responseBean.getError());
		}

		String decodedJson = new String(Base64.getDecoder().decode(responseBean.getData()));

		return mapper.readTree(decodedJson);
	}

	public List<Gstr3BMonthlyDto> getGstr3BSummaryReport(String userName, String gstin, String fy) throws Exception {

		long startTime = System.currentTimeMillis();

		log.info("Starting GSTR3B Summary Report GSTIN={} FY={}", gstin, fy);

		MasterData masterData = masterDataService.getMasterdatabyName(userName);

		if (masterData == null) {
			throw new RuntimeException("MasterData not found for user : " + userName);
		}

		GSTUserSession session = gstUserSessionServices.getUserSessionsByName(userName);

		if (session == null) {
			throw new RuntimeException("GST Session not found for user : " + userName);
		}

		List<String> periods = generateReturnPeriods(fy);

		log.info("Return Periods : {}", periods);

		List<Gstr3BMonthlyDto> result = new ArrayList<>();

		for (String retPeriod : periods) {

			try {

				log.info("Processing Return Period : {}", retPeriod);

				String response = callEnfr3BApi(gstin, retPeriod, masterData, session);

				// log.info("Raw GSTN Response : {}", response);

				JsonNode root = mapper.readTree(response);

				String statusCd = root.path("status_cd").asText();

				log.info("GSTN Status : {}", statusCd);

				if (!"1".equals(statusCd)) {

					log.warn("No data found for period {} Response={}", retPeriod, response);

					continue;
				}

				String encodedData = root.path("data").asText();

				if (encodedData == null || encodedData.isBlank()) {

					log.warn("Blank data received for period {}", retPeriod);
					continue;
				}

				String decodedJson = new String(Base64.getDecoder().decode(encodedData));

				log.info("Decoded Json For {} : {}", retPeriod, decodedJson);

				JsonNode json = mapper.readTree(decodedJson);

				Gstr3BMonthlyDto dto = convertDecodedJsonToDto(json);

				result.add(dto);

				log.info("Successfully Added Record For {}", retPeriod);

			} catch (Exception ex) {

				log.error("Error Processing Return Period : {}", retPeriod, ex);
			}
		}

		log.info("Total Records Found : {}", result.size());

		log.info("Completed In {} ms", System.currentTimeMillis() - startTime);

		return result;
	}

	private String callEnfr3BApi(String gstin, String retPeriod, MasterData masterData, GSTUserSession session)
			throws Exception {

		Map<String, String> params = new HashMap<>();

		params.put("action", "ENFR3BDET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);

		APIDetails apiDetails = apiDetailsImpl.findByName("ENFR3BDET");

		if (apiDetails == null) {
			throw new RuntimeException("API Details not configured for ENFR3BDET");
		}

		HttpHeaders headers = getDefaultHeaders(masterData, session.getAuthToken());

		String apiPath = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
				params);

		log.info("Calling GSTN API : {}", apiPath);

		GSTCommonResponseBean responseBean = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

		if (responseBean == null) {
			throw new RuntimeException("Null response received from GSTN");
		}

		log.info("GSTN Status : {}", responseBean.getStatus_cd());

		return mapper.writeValueAsString(responseBean);
	}

	private BigDecimal getDecimal(JsonNode root, String path) {

		JsonNode node = root.at(path);

		if (node == null || node.isMissingNode() || node.isNull()) {
			return BigDecimal.ZERO;
		}

		try {
			return node.decimalValue();
		} catch (Exception ex) {
			return BigDecimal.ZERO;
		}
	}

	private BigDecimal nvl(BigDecimal value) {
		return value == null ? BigDecimal.ZERO : value;
	}

	private Gstr3BMonthlyDto convertDecodedJsonToDto(JsonNode json) {

		Gstr3BMonthlyDto dto = new Gstr3BMonthlyDto();

		try {

			dto.setMonth(formatMonth(json.path("ret_period").asText()));
			dto.setFilingDate(json.path("dof").asText(""));

			dto.setTurnover(getDecimal(json, "/sup_details/osup_det/txval"));

			// ----------------------------------
			// TAX LIABILITY
			// ----------------------------------

			dto.setIgstLiability(getDecimal(json, "/sup_details/osup_det/iamt"));

			dto.setCgstLiability(getDecimal(json, "/sup_details/osup_det/camt"));

			dto.setSgstLiability(getDecimal(json, "/sup_details/osup_det/samt"));

			dto.setCessLiability(getDecimal(json, "/sup_details/osup_det/csamt"));

			dto.setTaxLiability(nvl(dto.getIgstLiability()).add(nvl(dto.getCgstLiability()))
					.add(nvl(dto.getSgstLiability())).add(nvl(dto.getCessLiability())));

			// ----------------------------------
			// REVERSE CHARGE
			// ----------------------------------

			dto.setIgstReverseCharge(getDecimal(json, "/sup_details/isup_rev/iamt"));

			dto.setCgstReverseCharge(getDecimal(json, "/sup_details/isup_rev/camt"));

			dto.setSgstReverseCharge(getDecimal(json, "/sup_details/isup_rev/samt"));

			dto.setReverseCharge(getDecimal(json, "/sup_details/isup_rev/txval"));

			// ----------------------------------
			// ITC AVAILED
			// ----------------------------------

			BigDecimal igstItcAvailed = BigDecimal.ZERO;
			BigDecimal cgstItcAvailed = BigDecimal.ZERO;
			BigDecimal sgstItcAvailed = BigDecimal.ZERO;

			JsonNode itcAvlArray = json.path("itc_elg").path("itc_avl");

			if (itcAvlArray.isArray()) {

				for (JsonNode node : itcAvlArray) {

					igstItcAvailed = igstItcAvailed.add(getDecimal(node, "/iamt"));
					cgstItcAvailed = cgstItcAvailed.add(getDecimal(node, "/camt"));
					sgstItcAvailed = sgstItcAvailed.add(getDecimal(node, "/samt"));

				}
			}

			dto.setIgstItcAvailed(igstItcAvailed);
			dto.setCgstItcAvailed(cgstItcAvailed);
			dto.setSgstItcAvailed(sgstItcAvailed);

//			dto.setIgstItcAvailed(getDecimal(json, "/itc_elg/itc_avl/0/iamt"));
//
//			dto.setCgstItcAvailed(getDecimal(json, "/itc_elg/itc_avl/0/camt"));
//
//			dto.setSgstItcAvailed(getDecimal(json, "/itc_elg/itc_avl/0/samt"));

			// ----------------------------------
			// ITC REVERSE
			// ----------------------------------

			dto.setIgstItcReverse(getDecimal(json, "/itc_elg/itc_rev/0/iamt"));

			dto.setCgstItcReverse(getDecimal(json, "/itc_elg/itc_rev/0/camt"));

			dto.setSgstItcReverse(getDecimal(json, "/itc_elg/itc_rev/0/samt"));

			dto.setItcReverse(
					nvl(dto.getIgstItcReverse()).add(nvl(dto.getCgstItcReverse())).add(nvl(dto.getSgstItcReverse())));

			// ----------------------------------
			// NET ITC
			// ----------------------------------

			dto.setIgstNetItc(getDecimal(json, "/itc_elg/itc_net/iamt"));

			dto.setCgstNetItc(getDecimal(json, "/itc_elg/itc_net/camt"));

			dto.setSgstNetItc(getDecimal(json, "/itc_elg/itc_net/samt"));

			dto.setCessNetItc(getDecimal(json, "/itc_elg/itc_net/csamt"));

			dto.setNetItcAvailable(nvl(dto.getIgstNetItc()).add(nvl(dto.getCgstNetItc())).add(nvl(dto.getSgstNetItc()))
					.add(nvl(dto.getCessNetItc())));

			// ----------------------------------
			// ITC PAYMENT
			// ----------------------------------

			dto.setIgstItc(getDecimal(json, "/tx_pmt/pditc/i_pdc"));

			dto.setCgstItc(getDecimal(json, "/tx_pmt/pditc/c_pdc"));

			dto.setSgstItc(getDecimal(json, "/tx_pmt/pditc/s_pds"));

			dto.setCessItc(getDecimal(json, "/tx_pmt/pditc/cs_pdcs"));

			dto.setTotalItcTax(nvl(dto.getIgstItc()).add(nvl(dto.getCgstItc())).add(nvl(dto.getSgstItc()))
					.add(nvl(dto.getCessItc())));

			dto.setTotalItcInterest(BigDecimal.ZERO);
			dto.setTotalItcLateFee(BigDecimal.ZERO);

			// ----------------------------------
			// RC CASH
			// ----------------------------------

			JsonNode pdcashArray = json.path("tx_pmt").path("pdcash");

			if (pdcashArray.isArray()) {

				for (JsonNode node : pdcashArray) {

					if (node.path("trans_typ").asInt() == 30003) {

						dto.setIgstRcCash(getDecimal(node, "/ipd"));

						dto.setCgstRcCash(getDecimal(node, "/cpd"));

						dto.setSgstRcCash(getDecimal(node, "/spd"));

						break;
					}
				}
			}

			dto.setCessRcCash(BigDecimal.ZERO);

			// ----------------------------------
			// CASH PAYMENT
			// ----------------------------------

			BigDecimal cashIgst = BigDecimal.ZERO;
			BigDecimal cashCgst = BigDecimal.ZERO;
			BigDecimal cashSgst = BigDecimal.ZERO;

			if (pdcashArray.isArray()) {

				for (JsonNode node : pdcashArray) {

					if (node.path("trans_typ").asInt() == 30002) {

						cashIgst = getDecimal(node, "/ipd");
						cashCgst = getDecimal(node, "/cpd");
						cashSgst = getDecimal(node, "/spd");

						break;
					}
				}
			}

			dto.setCashIgst(cashIgst);
			dto.setCashCgst(cashCgst);
			dto.setCashSgst(cashSgst);
			dto.setCashCess(BigDecimal.ZERO);

			dto.setTotalCashTax(cashIgst.add(cashCgst).add(cashSgst));

			// ----------------------------------
			// INTEREST
			// ----------------------------------

			dto.setInterest(getDecimal(json, "/intr_ltfee/intr_details/iamt")
					.add(getDecimal(json, "/intr_ltfee/intr_details/camt"))
					.add(getDecimal(json, "/intr_ltfee/intr_details/samt")));

			// ----------------------------------
			// LATE FEE
			// ----------------------------------

			dto.setLateFee(getDecimal(json, "/intr_ltfee/ltfee_details/iamt")
					.add(getDecimal(json, "/intr_ltfee/ltfee_details/camt"))
					.add(getDecimal(json, "/intr_ltfee/ltfee_details/samt")));

			dto.setTotalCashInterest(dto.getInterest());
			dto.setTotalCashLateFee(dto.getLateFee());

		} catch (Exception ex) {

			log.error("GSTR3B DTO Conversion Failed", ex);
		}

		return dto;
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
}