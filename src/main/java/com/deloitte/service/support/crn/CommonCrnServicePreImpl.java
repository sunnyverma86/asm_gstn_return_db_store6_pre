package com.deloitte.service.support.crn;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.filecounter.CrnDetailCommon;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommonCrnServicePreImpl {

	private static final int PAGE_SIZE = 200;
	private static final int BATCH_SIZE = 200;

	private static final String USERNAME = "GSTG2G18";

	private static final String STATE_CD = "18";

	@Autowired
	protected CommonCrnDateRepository commonCrnDateRepository;

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

	@Value("${file.dir.adjudication}")
	protected String fileDirAdjudicationLocation;

	private String getApiConstantByCaseType(String caseType) {

		switch (caseType) {

		// Refund
		case "RFUND":
			return Constants.GET_RETURN_FILE_DETAIL_REFUND_DATA;
			
		case "AMYDT":
			return Constants.GET_RETURN_FILE_DETAIL_AMYDT_DATA;

		// Recovery
		case "RCMOR":
			return Constants.GET_RETURN_FILE_DETAIL_RECOVERY_DATA;

		// Audit
		case "AUDIT":
			return Constants.GET_RETURN_FILE_DETAIL_AUDIT_DATA;

		// Appeal
		case "APLTD":
		case "RVORD":
		case "APLHA":
			return Constants.GET_RETURN_FILE_DETAIL_Appeal_Tax_department;

		// Adjudication
		case "ADJVP":
		case "ADJUR":
		case "ADJND":
		case "ADJSA":
		case "ADJSR":
		case "ADJRA":
		case "ADJRC":
		case "ADJRO":
		case "ADJAT":
		case "ADJPA":
		case "ADJNF":
		case "ADJGP":
		case "ADJDT":
			return Constants.GET_RETURN_FILE_DETAIL_Adjudication_Determination_Tax_DATA;

		case "APPEL":
			return Constants.GET_RETURN_FILE_DETAIL_Adjudication_APPEL;
		default:
			return null;
		}
	}

	private Map<String, String> getParamsByCaseType(String caseType, String statecd, String crn) {

		switch (caseType) {

// Adjudication
		case "ADJDT":
		case "ADJGP":
		case "ADJND":
		case "ADJNF":
		case "ADJPA":
		case "ADJAT":
		case "ADJRA":
		case "ADJSR":
		case "ADJSA":
		case "ADJUR":
		case "ADJVP":
		case "ADJAE":
		case "ADJRC":
		case "ADJRO": // added
			return getParamsForGetReturnFileDetails(statecd, crn);

		case "APPEL":
			return getParamsForGetReturnFileDetailsAPPEL(statecd, crn);
			
		case "AMYDT": // added
			return getParamsForGetReturnFileDetailsAMYDT(statecd, crn);

// Refund
		case "RFUND": // corrected from Rfund
			return getParamsForGetReturnFileDetailsRFUND(statecd, crn);

// Advance ruling
		case "ARAPA":
		case "ARARA":
		case "ARARF":
			return getParamsForGetReturnFileDetailsARAPA(statecd, crn);

// Recovery
		case "RCMOR":
			return getParamsForGetReturnFileDetailsRCMOR(statecd, crn);

// Recovery
		case "AUDIT":
			return getParamsForGetReturnFileDetailsAUDIT(statecd, crn);

// 👉 RCPID
		case "RCPID":
			return getParamsForGetReturnFileDetailsRCPID(statecd, crn);

// Appeal
		case "APLHA":
		case "RVORD":
		case "APLTD":
		case "APPEAL":
			return getParamsForGetReturnFileDetailsAPLHA(statecd, crn);

		default:
			return null; // don't throw exception
		}
	}

	// ADJDT,ADJGP,ADJND,ADJNF,ADJPA,ADJAT,ADJRA,ADJSA,ADJSR,ADJUR,ADJVP,ADJAE,

	private Map<String, String> getParamsForGetReturnFileDetails(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETCASEDATAASSMT");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}
	
	private Map<String, String> getParamsForGetReturnFileDetailsAMYDT(String statecd, String crn) {//Adjudication-Get Case Data-MFY
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "MFYGETCASE");
		params.put("state_cd", statecd);
		params.put("crn", crn);
		return params;
	}
	
//	private Map<String, String> getParamsForGetReturnFileDetailsAMYDT(String statecd, String crn) {//Adjudication-Get Case Data-Reply-MFY
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("action", "MFYREPLY");
//		params.put("state_cd", statecd);
//		params.put("crn", crn);
//		return params;
//	}
//	
//	private Map<String, String> getParamsForGetReturnFileDetailsAMYDT(String statecd, String crn) {//Adjudication-Get Case Data-Reply-MFY
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("action", "MFYVOLPAYMENT");
//		params.put("state_cd", statecd);
//		params.put("crn", crn);
//		return params;
//	}


	private Map<String, String> getParamsForGetReturnFileDetailsAPPEL(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "GETDATA");
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

	// AUDIT
	private Map<String, String> getParamsForGetReturnFileDetailsAUDIT(String statecd, String crn) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "AUDCSINF");
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

	public String processPendingCrns() {

		long startTime = System.currentTimeMillis();

		int pageNo = 0;
		int successCount = 0;
		int failCount = 0;
		int skippedCount = 0;
		int totalProcessed = 0;

		log.info("==================================================");
		log.info("🚀 CRN PROCESS STARTED");
		log.info("==================================================");

		try {

			// =====================================================
			// MASTER DATA
			// =====================================================

			MasterData masterData = masterDataService.getMasterdatabyName(USERNAME);

			if (masterData == null) {

				log.error("❌ MasterData not found for user={}", USERNAME);

				return "MASTERDATA_NOT_FOUND";
			}

			// =====================================================
			// SESSION
			// =====================================================

			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(USERNAME);

			if (session == null) {

				log.error("❌ Session not found for user={}", USERNAME);

				return "SESSION_NOT_FOUND";
			}

			// =====================================================
			// HEADERS
			// =====================================================

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData);

			headers.set("auth-token", session.getAuthToken());
			headers.set("content-type", "application/json");

			// =====================================================
			// PAGINATION LOOP
			// =====================================================

			while (true) {

				Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);

				 Page<CrnDetailCommon> page =
				 crnDetailCommonRepository.findPendingRecordsAfterId(77732L, pageable);

//				Page<CrnDetailCommon> page = crnDetailCommonRepository
//						.findByIdReturnCountCrnJsonGreaterThanAndIsSuccessFalseAndIsFutureFalseAndIsProcessedFalseAndCounterAttemptLessThanOrderByIdDesc(
//								77732L, 3, pageable);

				// =====================================================
				// PAGE LOGGING
				// =====================================================

				log.info("======================================================");
				log.info("📄 PAGE NUMBER           : {}", pageNo);
				log.info("📦 PAGE SIZE             : {}", PAGE_SIZE);
				log.info("📊 RECORDS IN THIS PAGE  : {}", page.getNumberOfElements());
				log.info("📚 TOTAL PENDING RECORDS : {}", page.getTotalElements());
				log.info("📑 TOTAL PAGES           : {}", page.getTotalPages());
				log.info("======================================================");

				// =====================================================
				// NO MORE RECORDS
				// =====================================================

				if (page.isEmpty()) {

					log.info("✅ No more pending records found");

					break;
				}

				log.info("--------------------------------------------------");
				log.info("📄 Processing Page={} | Records={}", pageNo, page.getNumberOfElements());
				log.info("--------------------------------------------------");

				List<CrnDetailCommon> batchList = new ArrayList<>();

				// =====================================================
				// TOTAL PENDING RECORDS LOG
				// =====================================================

				long pendingCount = crnDetailCommonRepository.countPendingRecords();

				log.info("======================================================");
				log.info("📚 TOTAL PENDING RECORDS IN DB : {}", pendingCount);
				log.info("======================================================");

				// =====================================================
				// RECORD LOOP
				// =====================================================

				for (CrnDetailCommon record : page.getContent()) {

					String crn = record.getCrn();

					String caseType = record.getOriginalCaseTyp();

					String apiPath = null;
					try {

						log.info("--------------------------------------------------");
						log.info("🔄 Processing Record No : {}", totalProcessed + 1);
						log.info("🧾 CRN                  : {}", crn);
						log.info("📂 CASE TYPE            : {}", caseType);
						log.info("--------------------------------------------------");

						// =================================================
						// STEP 1 : GET API CONSTANT
						// =================================================

						String apiConstant = getApiConstantByCaseType(caseType);

						if (apiConstant == null) {

							log.warn("⚠️ API Constant not developed | CRN={} | caseType={}", crn, caseType);

							record.setIsSuccess(false);
							record.setCounterAttempt(record.getCounterAttempt() + 1);
							record.setIsFuture(true);
							record.setIsProcessed(false);

							// =========================================
							// CHANGED FOR JSONB MSG
							// =========================================
							record.setMsg(objectMapper.valueToTree(Map.of("message", "Params Not Developed")));

							skippedCount++;

							batchList.add(record);

							continue;
						}

						// =================================================
						// STEP 2 : PARAMS
						// =================================================

						Map<String, String> params = getParamsByCaseType(caseType, STATE_CD, crn);

						if (params == null) {

							log.warn("⚠️ Params mapping missing | CRN={} | caseType={}", crn, caseType);

							record.setIsSuccess(false);
							record.setCounterAttempt(record.getCounterAttempt() + 1);
							record.setIsProcessed(false);

							// =========================================
							// CHANGED FOR JSONB MSG
							// =========================================

							record.setMsg(objectMapper.valueToTree(Map.of("message", "Params Not Developed")));

							skippedCount++;

							batchList.add(record);

							continue;
						}

						// =================================================
						// STEP 3 : API DETAILS
						// =================================================

						APIDetails apiDetails = apiDetailsImpl.findByName(apiConstant);

						if (apiDetails == null) {

							log.warn("⚠️ API Details missing | CRN={} | apiConstant={}", crn, apiConstant);

							record.setIsSuccess(false);
							record.setCounterAttempt(record.getCounterAttempt() + 1);
							record.setIsProcessed(false);

							// =========================================
							// CHANGED FOR JSONB MSG
							// =========================================

							record.setMsg(objectMapper.valueToTree(Map.of("message", "API Details Missing")));

							skippedCount++;

							batchList.add(record);

							continue;
						}

						// =================================================
						// STEP 4 : BUILD URL
						// =================================================

						apiPath = authenticationHelper
								.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

						log.info("📡 API PATH={}", apiPath);

						// =================================================
						// STEP 5 : API CALL
						// =================================================

						GSTCommonResponseBean response = restClient.get(apiPath, GSTCommonResponseBean.class, headers);

						JsonNode responseJson;
						// check
//						if (caseType.equalsIgnoreCase("APPEL") && response != null
//								&& "1".equals(response.getStatus_cd())) {
//							System.out.println("APPEL");
//						}

						// =================================================s
						// SUCCESS RESPONSE
						// =================================================

						if (response != null && "1".equals(response.getStatus_cd())) {

							byte[] decodedBytes = Base64.getDecoder().decode(response.getData());

							String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);

							responseJson = objectMapper.readTree(decodedJson);

							record.setJsonData(responseJson);

							record.setIsSuccess(true);

							record.setIsProcessed(true);

							record.setUrl(apiPath);

							// =========================================
							// CHANGED FOR JSONB MSG
							// =========================================
							record.setMsg(objectMapper.valueToTree(Map.of("message", "SUCCESS")));

							successCount++;

							log.info("✅ CRN SUCCESS={}", crn);
						}

						// =================================================
						// FAILURE RESPONSE
						// =================================================

						else {

							responseJson = objectMapper.valueToTree(response);

							record.setJsonData(responseJson);

							record.setIsSuccess(false);
							record.setJsonData(responseJson);
							record.setCounterAttempt(record.getCounterAttempt() + 1);

							record.setIsProcessed(false);
							if (record.getOriginalCaseTyp().equalsIgnoreCase("RCMOR")) {
								record.setIsFuture(true);
							}

							record.setUrl(apiPath);

							String errorMessage = "API Failed";

							if (response != null && response.getError() != null
									&& response.getError().get("message") != null) {

								errorMessage = response.getError().get("message");
							}

							// =========================================
							// CHANGED FOR JSONB MSG
							// =========================================
							record.setMsg(objectMapper.valueToTree(Map.of("message", errorMessage)));

							failCount++;

							log.error("❌ API FAILED | CRN={} | msg={}", crn, errorMessage);
						}

					} catch (Exception e) {

						log.error("❌ CRN Processing Failed | CRN={} | caseType={} | error={}", crn, caseType);

						record.setIsSuccess(false);
						record.setIsFuture(true);
						record.setCounterAttempt(record.getCounterAttempt() + 1);
						record.setUrl(apiPath);
						record.setIsProcessed(false);

						// =========================================
						// CHANGED FOR JSONB MSG
						// =========================================
						record.setMsg(objectMapper.valueToTree(Map.of("message", e.getMessage())));

						failCount++;
					}

					batchList.add(record);

					totalProcessed++;

					// =====================================================
					// LIVE COUNTER LOG
					// =====================================================

					log.info("📊 TOTAL PROCESSED     : {}", totalProcessed);
					log.info("✅ SUCCESS COUNT       : {}", successCount);
					log.info("❌ FAIL COUNT          : {}", failCount);
					log.info("⚠️ SKIPPED COUNT       : {}", skippedCount);

					// =================================================
					// BATCH SAVE
					// =================================================

					if (batchList.size() >= BATCH_SIZE) {

						crnDetailCommonRepository.saveAll(batchList);

						log.info("💾 Batch Saved Successfully");
						log.info("📦 Batch Size : {}", batchList.size());

						batchList.clear();
					}
				}

				// =====================================================
				// SAVE REMAINING RECORDS
				// =====================================================

				if (!batchList.isEmpty()) {

					crnDetailCommonRepository.saveAll(batchList);

					log.info("💾 Final Batch Saved");
					log.info("📄 Page Number : {}", pageNo);
					log.info("📦 Batch Size  : {}", batchList.size());
				}

				pageNo++;
			}

		} catch (Exception ex) {

			log.error("🔥 CRN PROCESS FAILED", ex);

			return "FAILED : " + ex.getMessage();
		}

		long endTime = System.currentTimeMillis();

		log.info("==================================================");
		log.info("🏁 CRN PROCESS COMPLETED");
		log.info("==================================================");
		log.info("📊 Total Processed={}", totalProcessed);
		log.info("✅ Success Count={}", successCount);
		log.info("❌ Fail Count={}", failCount);
		log.info("⏭️ Skipped Count={}", skippedCount);
		log.info("⏱️ Execution Time={} ms", (endTime - startTime));
		log.info("==================================================");

		return "Processed Successfully -> Total=" + totalProcessed + ", Success=" + successCount + ", Failed="
				+ failCount + ", Skipped=" + skippedCount;
	}

}
