package com.deloitte.service.abs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.CommonFileDetailsAdjudication;
import com.deloitte.returns.entity.filecounter.CrnDetailCommon;
import com.deloitte.returns.entity.filecounter.ReturnCountCrnJson;
import com.deloitte.returns.repository.ReturnCountCrnJsonRepository;
import com.deloitte.returns.repository.common.CommonCrnDateRepository;
import com.deloitte.returns.repository.common.CommonFileDetailsAdjudicationRepository;
import com.deloitte.returns.repository.common.CrnDetailCommonRepository;
import com.deloitte.returns.repository.common.ReturnCountCrnRepository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.service.support.CommonServiceGstrImplSupport;
import com.deloitte.service.utility.procedure.CallInsertCrnProcedure;
import com.deloitte.service.utility.procedure.CrnProcedureService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractCommonCrnServiceImpl {

	@Autowired
	protected CommonCrnDateRepository commonCrnDateRepository;

	@Autowired
	protected CrnProcedureService crnProcedureService;

//	@Autowired
//	protected Map<String, CrnCaseHandler> crnCaseHandlers;

	@Autowired
	protected MasterDataService masterDataService;

	@Autowired
	protected GstUserSessionServices gstUserSessionServices;

	@Autowired
	protected RestClientHelper restClient;

	@Autowired
	protected CommonServiceGstrImplSupport commonServiceGstrImplSupport;

	@Autowired
	protected AuthenticationHelper authenticationHelper;

	@Autowired
	protected APIDetailsImpl apiDetailsImpl;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected ReturnCountCrnRepository returnCountCrnRepository;

	@Autowired
	protected ReturnCountCrnJsonRepository returnCountCrnJsonRepository;

	@Autowired
	protected CommonFileDetailsAdjudicationRepository commonFileDetailsAdjudicationRepository;

	@Autowired
	protected CrnDetailCommonRepository crnDetailCommonRepository;

	@Autowired
	protected CallInsertCrnProcedure callInsertCrnProcedure;

	@Value("${file.dir.adjudication}")
	protected String fileDirAdjudicationLocation;

	// 🔥 Each child will provide this
	protected abstract String getApiConstant();

	public String processCommon(ReturnCountCrnJson returnCountJsonCrn, MasterData masterData, String startDateTime,
			String endDateTime, GSTUserSession gstUserSessions, String caseType) {

		log.info("📡 Processing caseType={} start={}", caseType, startDateTime);

		try {

			APIDetails apiDetails = apiDetailsImpl.findByName(getApiConstant()); // 🔥 dynamic

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData);
			headers.set("auth-token", gstUserSessions.getAuthToken());
			headers.set("content-type", apiDetails.getApiContentType());

			JsonNode jsonNode = returnCountJsonCrn.getJsonData();

			if (jsonNode == null || !jsonNode.has("crnlist")) {
				return "NO_DATA";
			}

			JsonNode crnArray = jsonNode.get("crnlist");

			String stateCd = masterData.getStateCd();
			String formattedStartDate = startDateTime.replace(":", "_");

			for (JsonNode crnNode : crnArray) {

				String crn = crnNode.path("crn").asText();
				String originalStatus = crnNode.path("status").asText();
				String originalCaseTyp = crnNode.path("casetyp").asText();
				String approvAuth = crnNode.path("approvAuth").asText();
				String originalUpdateTmstmp = crnNode.path("updateTmstmp").asText();

				String statusType = "SUCCESS";
				String url = "Success";
				JsonNode responseJsonNode = null;

				try {

					// Map<String, String> params = getParamsForGetReturnFileDetails(stateCd, crn);
					Map<String, String> params = getParamsByCaseType(caseType, stateCd, crn);

					String apiPath = authenticationHelper
							.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

					GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

					if (response == null || !"1".equals(response.getStatus_cd())) {

						statusType = "FAIL";
						url = apiPath;
						responseJsonNode = objectMapper.valueToTree(response);

					} else {

						byte[] decodedBytes = Base64.getDecoder().decode(response.getData());
						String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);
						responseJsonNode = objectMapper.readTree(decodedJson);
					}

					String fileName = buildFileName(formattedStartDate, crn, statusType);
					String folderPath = buildFolderPath(caseType);

					saveFileDetails(fileName, folderPath, startDateTime, endDateTime, crn, statusType, responseJsonNode,
							url, returnCountJsonCrn.getId(), originalStatus, originalCaseTyp, approvAuth,
							originalUpdateTmstmp);

				} catch (Exception ex) {
					log.error("Error CRN={}", crn, ex);
				}
			}

			return "SUCCESS";

		} catch (Exception ex) {
			log.error("🔥 Error caseType={}", caseType, ex);
			return "FAILED";
		}
	}

	private String buildFileName(String startDateTime, String crn, String statusType) {

		return String.format("%s_%s_%s.json", startDateTime, statusType, crn);
	}

//	private String buildFolderPath(String caseType) {
//
//		return Paths.get(fileDirAdjudicationLocation, AdjudicationDeterminationTax.class.getSimpleName().toUpperCase(),
//				caseType).toString() + File.separator;
//	}

	private String buildFolderPath(String caseType) {

		return Paths.get(fileDirAdjudicationLocation, caseType).toString() + File.separator;
	}

	private void saveFileDetails(String fileName, String folderPath, String startDateTime, String endDateTime,
			String crn, String statusType, JsonNode jsonData, String url, Long idReturnCountCrnJson,
			String originalStatus, String originalCaseTyp, String originalApprovAuth, String originalUpdateTmstmp) {

		CommonFileDetailsAdjudication entity = new CommonFileDetailsAdjudication();
		CrnDetailCommon crnDetailCommon = new CrnDetailCommon();
		entity.setFileName(fileName);
		entity.setFilePath(folderPath + fileName);
		entity.setStartDateTime(startDateTime);
		entity.setEndDateTime(endDateTime);
		entity.setCrn(crn);
		entity.setIdReturnCountCrnJson(idReturnCountCrnJson);
		entity.setUrl(url);
		entity.setStatusType(statusType);
		entity.setApplication(originalCaseTyp);

		// entity.setJsonData(jsonData);
		entity.setIsProcessed(false);
		crnDetailCommon.setCrn(crn);
		crnDetailCommon.setIdReturnCountCrnJson(idReturnCountCrnJson);
		crnDetailCommon.setIsProcessed(false);
		crnDetailCommon.setJsonData(jsonData);
		crnDetailCommon.setOriginalApprovAuth(originalApprovAuth);
		crnDetailCommon.setOriginalCaseTyp(originalCaseTyp);
		crnDetailCommon.setOriginalStatus(originalStatus);
		crnDetailCommon.setOriginalUpdateTmstmp(originalUpdateTmstmp);

		commonFileDetailsAdjudicationRepository.save(entity);
		crnDetailCommonRepository.save(crnDetailCommon);
		writeJson(folderPath, fileName, jsonData);

		log.info("💾 File metadata + JSON saved in DB for CRN={}", crn);
	}

	private void writeJson(String folderPath, String fileName, JsonNode jsonData) {

		File directory = new File(folderPath);

		try {

			// Create folder if not exists
			if (!directory.exists()) {
				boolean created = directory.mkdirs();
				if (created) {
					log.info("📁 Directory created: {}", folderPath);
				}
			}

			File jsonFile = new File(directory, fileName);

			objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonData);

			log.debug("📄 JSON file written successfully: {}", jsonFile.getAbsolutePath());

		} catch (IOException e) {

			log.error("❌ Error writing JSON file | folderPath={} | fileName={}", folderPath, fileName, e);

		}
	}

	private Map<String, String> getParamsByCaseType(String caseType, String statecd, String crn) {

		switch (caseType) {

		// 👉 ADJ + APPEL
		case "ADJDT":
		case "ADJGP":
		case "ADJND":
		case "ADJNF":
		case "ADJPA":
		case "ADJAT":
		case "ADJRA":
		case "ADJSA":
		case "ADJSR":
		case "ADJUR":
		case "ADJVP":
		case "ADJAE":
		case "APPEL":
			return getParamsForGetReturnFileDetails(statecd, crn);

		// 👉 Refund
		case "Rfund":
			return getParamsForGetReturnFileDetailsRFUND(statecd, crn);

		// 👉 Advance Ruling
		case "ARAPA":
		case "ARARA":
		case "ARARF":
			return getParamsForGetReturnFileDetailsARAPA(statecd, crn);

		// 👉 RCPID
		case "RCPID":
			return getParamsForGetReturnFileDetailsRCPID(statecd, crn);

		// 👉 Recovery
		case "RCMOR":
			return getParamsForGetReturnFileDetailsRCMOR(statecd, crn);

		// 👉 Appeal
		case "APLHA":
		case "RVORD":
		case "APLTD":
		case "APPEAL":
			return getParamsForGetReturnFileDetailsAPLHA(statecd, crn);

		default:
			throw new IllegalArgumentException("❌ Unsupported caseType: " + caseType);
		}
	}

	// ADJDT,ADJGP,ADJND,ADJNF,ADJPA,ADJAT,ADJRA,ADJSA,ADJSR,ADJUR,ADJVP,ADJAE,
	// //APPEL
	private Map<String, String> getParamsForGetReturnFileDetails(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATAASSMT");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

	// Rfund //
	private Map<String, String> getParamsForGetReturnFileDetailsRFUND(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATA");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

	// ARAPA,ARARA,ARARF
	private Map<String, String> getParamsForGetReturnFileDetailsARAPA(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATAAR");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

	// RCPID
	private Map<String, String> getParamsForGetReturnFileDetailsRCPID(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "RCPID");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

	// RCMOR
	private Map<String, String> getParamsForGetReturnFileDetailsRCMOR(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATARECOVERY");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

	// APLHA,RVORD,APLTD,APPEAL
	private Map<String, String> getParamsForGetReturnFileDetailsAPLHA(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATAPPEAL");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}

}
