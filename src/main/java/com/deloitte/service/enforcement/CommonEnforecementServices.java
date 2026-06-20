package com.deloitte.service.enforcement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.EnforcementOfficerGSTR1;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.FileNameEnforcement;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.GstinEnforecementGstr1;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1Sum1.EnforcementOfficerGSTR1sum;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1Sum1.GstinEnforecementGstr1sum;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1.EnforcementOfficerGSTR2A;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1.GstinEnforecementGstr2A;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1.EnforcementOfficerGSTR3B;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1.GstinEnforecementGstr3b;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1.EnforcementOfficerGSTR4;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1.GstinEnforecementGstr4;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1.EnforcementOfficerGSTR5;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1.GstinEnforecementGstr5;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1.EnforcementOfficerGSTR6;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1.GstinEnforecementGstr6;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR71.EnforcementOfficerGSTR7;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR71.GstinEnforecementGstr7;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1.EnforcementOfficerGSTR8;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1.GstinEnforecementGstr8;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1.EnforcementOfficerGSTR9;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1.GstinEnforecementGstr9;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1.EnforcementOfficerRecordSearchEnforcement;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchEnforcement1.GstinEnforcementRSE;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1.EnforcementOfficerRecordSearchPayments;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1.GstinEnforcementRSP;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1.EnforcementOfficerRecordSearchRegistration;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchRegistration1.GstinEnforcementRSRegis;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1.EnforcementOfficerRecordSearchReturns;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchReturns1.GstinEnforcementRSR;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR1Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR1SumRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR2ARepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR3BRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR4Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR5Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR6Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR7Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR8Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR9Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchPaymentsRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchRegistrationRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchReturnsRepository;
import com.deloitte.returns.repository.enforcement.FileDetaisServiceEnforcementImpl;
import com.deloitte.returns.repository.enforcement.FileNameEnforcementRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSERepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSPRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSRRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSRegisRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr1Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr1SumRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr2ARepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr3bRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr4Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr5Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr6Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr7Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr8Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr9Repository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.service.support.FileDownloadHelperForEnforcement;
import com.deloitte.service.utility.LogbackConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommonEnforecementServices {

	// common-autowired-start
	private static final int BATCH_SIZE = 150; // Adjust the batch size as needed
	private static final int THREAD_POOL_SIZE = 15; // Adjust the number of threads as needed

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private GstUserSessionServices gstUserSessionServices;

	@Autowired
	private APIDetailsImpl apiDetailsImpl;

	@Autowired
	private AuthenticationHelper authenticationHelper;

	@Autowired
	private RestClientHelper restClient;

	@Autowired
	private GstinEnforecementGstr1Repository gstinEnforecementGstr1Repository;

	@Autowired
	private GstinEnforecementGstr3bRepository gstinEnforecementGstr3bRepository;

	@Autowired
	private GstinEnforecementGstr7Repository gstinEnforecementGstr7Repository;

	@Autowired
	private GstinEnforecementGstr2ARepository gstinEnforecementGstr2ARepository;

	@Autowired
	private GstinEnforecementGstr4Repository gstinEnforecementGstr4Repository;

	@Autowired
	private GstinEnforecementGstr5Repository gstinEnforecementGstr5Repository;

	@Autowired
	private GstinEnforecementGstr6Repository gstinEnforecementGstr6Repository;

	@Autowired
	private GstinEnforecementGstr1SumRepository gstinEnforecementGstr1SumRepository;

	@Autowired
	private GstinEnforecementGstr8Repository gstinEnforecementGstr8Repository;

	@Autowired
	private GstinEnforecementGstr9Repository gstinEnforecementGstr9Repository;

	@Autowired
	private GstinEnforcementRSERepository gstinEnforcementRSERepository;

	@Autowired
	private GstinEnforcementRSPRepository gstinEnforcementRSPRepository;

	@Autowired
	private GstinEnforcementRSRegisRepository gstinEnforcementRSRegisRepository;

	@Autowired
	private GstinEnforcementRSRRepository gstinEnforcementRSRRepository;

	@Autowired
	private LogbackConfig logbackConfig;

	@Autowired
	private FileDetaisServiceEnforcementImpl fileDetaisServiceImpl;

	@Autowired
	private FileNameEnforcementRepository fileNameEnforcementRepository;

	@Autowired
	private EnforcementOfficerGSTR1Repository enforcementOfficerGSTR1Repository;

	@Autowired
	private EnforcementOfficerGSTR3BRepository enforcementOfficerGSTR3BRepository;

	@Autowired
	private EnforcementOfficerGSTR2ARepository enforcementOfficerGSTR2ARepository;

	@Autowired
	private EnforcementOfficerGSTR7Repository enforcementOfficerGSTR7Repository;

	@Autowired
	private EnforcementOfficerGSTR4Repository enforcementOfficerGSTR4Repository;

	@Autowired
	private EnforcementOfficerGSTR5Repository enforcementOfficerGSTR5Repository;

	@Autowired
	private EnforcementOfficerGSTR6Repository enforcementOfficerGSTR6Repository;

	@Autowired
	private EnforcementOfficerGSTR1SumRepository enforcementOfficerGSTR1SumRepository;

	@Autowired
	private EnforcementOfficerGSTR8Repository enforcementOfficerGSTR8Repository;

	@Autowired
	private EnforcementOfficerGSTR9Repository enforcementOfficerGSTR9Repository;

	@Autowired
	private EnforcementOfficerRecordSearchRegistrationRepository enforcementOfficerRecordSearchRegistrationRepository;

	@Autowired
	private EnforcementOfficerRecordSearchPaymentsRepository enforcementOfficerRecordSearchPaymentsRepository;

	@Autowired
	private EnforcementOfficerRecordSearchReturnsRepository enforcementOfficerRecordSearchReturnsRepository;

	private String lastDecodedJson;

	// common-autowired-end
	public String getCommonGstrAndOtherDownloadLatest(String username, String date, String application, String gstin,
			String retPeriod, String section) {
		log.info("getCommonGstrAndOtherDownloadLatest APPLICATION:" + application);
		String response = StringUtils.EMPTY;
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			GSTUserSession gstUserSessions = gstUserSessionServices.getUserSessionsByName(username);
			if (null != gstUserSessions) {

				response = getFileDetails(username, masterData, date, gstUserSessions, application, gstin, retPeriod,
						section);
			}
		} else {
			log.error(" Return FileCount Response having issue");
		}
		return response;
	}

	private Map<String, String> getParamsForGetReturnFileDetails(String gstin, String retPeriod, String section) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR1DET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		params.put("sec_name", section);
		return params;
	}

	// OLD-TESTING PURPOSE START.. NOT IN USE
	private String getFileDetails(String username, MasterData masterData, String date, GSTUserSession gstUserSessions,
			String application, String gstin, String retPeriod, String section) {
		EnforcementOfficerGSTR1 enforcementOfficerGSTR1Obj = new EnforcementOfficerGSTR1();

		APIDetails apiDetailsForFileDetails = apiDetailsImpl
				.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR1_SECTIONS);

		HttpHeaders headersForFileDetails = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSessions.getAuthToken(), apiDetailsForFileDetails.getApiContentType());
		Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetails(gstin, retPeriod, section);
		String pathForFileDetails = authenticationHelper.getUriWithParam(
				authenticationHelper.getFullPath(masterData, apiDetailsForFileDetails), paramsForFileDetails);
		GSTCommonResponseBean requestEntityForFileDetails = restClient.get(pathForFileDetails,
				GSTCommonResponseBean.class, headersForFileDetails);
		if (requestEntityForFileDetails.getStatus_cd().equals("1")) {
			String decodedDataString = null;
//			try {
//				
//				byte[] decodedData = Base64.getDecoder().decode(requestEntityForFileDetails.getData());
//				// decodedDataString = new String(decodedData, StandardCharsets.UTF_8);
//				decodedDataString = new String(decodedData, "UTF-8"); // Throws checked exception
//				System.out.println(decodedDataString);
//				enforcementOfficerGSTR1Obj = new ObjectMapper().readValue(decodedDataString,
//						EnforcementOfficerGSTR1.class);
//				// System.out.println(AdjudicationDeterminationTaxObj);
//				List<CommonFileDetailsAdjudication> fileDetailsList = new ArrayList<>();
//
//				return enforcementOfficerGSTR1Obj.getCrn();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		return pathForFileDetails;
	}

	/////////////////// GSR1///////////////////////////////////
	public String getEnforcementGstr1DetailsAndSave(String username, List<String> sectionList) {

		MasterData masterData = masterDataService.getMasterdatabyName(username);

		if (masterData != null) {

			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR1_SECTIONS);

			List<GstinEnforecementGstr1> listOfData = gstinEnforecementGstr1Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

			listOfData.forEach(gstinEntry -> {

				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();

				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR1 temp = null;

				for (String section : sectionList) {

					log.info("Processing GSTR1 GSTIN " + gstinNumber + " Section " + section + " retPeriod: "
							+ retPeriod);

					Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetails(gstinNumber, retPeriod,
							section);

					String path = authenticationHelper.getUriWithParam(
							authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

					temp = getSectionObj(path, gstinNumber, masterData, apiDetails);

					if (temp == null) {
						log.info("No JSON received for GSTIN " + gstinNumber + " section " + section);
						continue;
					}

					if (section.equalsIgnoreCase("B2B")) {
						if (temp.getB2b() != null && !temp.getB2b().isEmpty()) {

							if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
									getFolderName(section, retPeriod), lastDecodedJson)) {

								fileDetailsList.add(enfgstr1getfileDetails(getFileName(gstinNumber),
										getFolderName(section, retPeriod), gstinNumber));
							}
						}
					}

					if (section.equalsIgnoreCase("B2BA")) {
						if (temp.getB2Ba() != null && !temp.getB2Ba().isEmpty()) {

							if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
									getFolderName(section, retPeriod), lastDecodedJson)) {

								fileDetailsList.add(enfgstr1getfileDetails(getFileName(gstinNumber),
										getFolderName(section, retPeriod), gstinNumber));
							}
						}
					}

					if (section.equalsIgnoreCase("CDN")) {
						if (temp.getCdn() != null && !temp.getCdn().isEmpty()) {

							if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
									getFolderName(section, retPeriod), lastDecodedJson)) {

								fileDetailsList.add(enfgstr1getfileDetails(getFileName(gstinNumber),
										getFolderName(section, retPeriod), gstinNumber));
							}
						}
					}

					if (section.equalsIgnoreCase("ISD")) {
						if (temp.getIsd() != null && !temp.getIsd().isEmpty()) {

							if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
									getFolderName(section, retPeriod), lastDecodedJson)) {

								fileDetailsList.add(enfgstr1getfileDetails(getFileName(gstinNumber),
										getFolderName(section, retPeriod), gstinNumber));
							}
						}
					}

				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr1 gstinObj = gstinEnforecementGstr1Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (gstinObj != null) {
					gstinObj.setIsProcessed(true);

					if (temp != null && temp.getB2b() != null && !temp.getB2b().isEmpty()) {
						gstinObj.setIsProcessed(true);
					} else {
						gstinObj.setFoundInfo(false);
					}

					gstinEnforecementGstr1Repository.save(gstinObj);
				}

			});
		}

		return "EnforcementOfficerGSTR1Temp values Saved";
	}

	private EnforcementOfficerGSTR1 getSectionObj(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR1 enforcementOfficerGSTR1 = new EnforcementOfficerGSTR1();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println(decodedDataString);
				lastDecodedJson = decodedDataString;

				enforcementOfficerGSTR1 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR1.class);

			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		}

		else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR1;
	}

	private String getFileName(String gstin) {
		return gstin.concat(".json");
	}

	// NEED TO WORKD HERE
	private String getFolderName(String section, String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR1.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\").append(section).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr1getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR1.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	/// SAVING
	/// -DATA/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String saveDataFromJsonToDb() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

		List<FileNameEnforcement> listofFileNameEnforcement = fileNameEnforcementRepository
				.findAllByIsProcessedFalseOrderById();
		int totalFiles = listofFileNameEnforcement.size();

		for (int i = 0; i < totalFiles; i += BATCH_SIZE) {
			int start = i;
			int end = Math.min(i + BATCH_SIZE, totalFiles);

			executorService.submit(() -> {
				for (int j = start; j < end; j++) {
					FileNameEnforcement fileNameEnforcement = listofFileNameEnforcement.get(j);
					String jsonFilePath = fileNameEnforcement.getFilePath();
					String application = fileNameEnforcement.getApplication();
					String jsonFileName = fileNameEnforcement.getFileName();

					try {

						if (jsonFileName.endsWith(".json")) {
							jsonFileName = jsonFileName.substring(0, jsonFileName.lastIndexOf(".json"));

						}

						if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR1")) {

							EnforcementOfficerGSTR1 enforcementOfficerGSTR1 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR1.class);
							enforcementOfficerGSTR1.setGstin(jsonFileName);

							EnforcementOfficerGSTR1 enforcementOfficerGSTR1Obj = enforcementOfficerGSTR1Repository
									.save(enforcementOfficerGSTR1);

							if (enforcementOfficerGSTR1Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR3B")) {

							EnforcementOfficerGSTR3B enforcementOfficerGSTR3B = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR3B.class);
							enforcementOfficerGSTR3B.setGstin(jsonFileName);
							EnforcementOfficerGSTR3B enforcementOfficerGSTR3BObj = enforcementOfficerGSTR3BRepository
									.save(enforcementOfficerGSTR3B);

							if (enforcementOfficerGSTR3BObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR2A")) {
							EnforcementOfficerGSTR2A enforcementOfficerGSTR2A = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR2A.class);
							enforcementOfficerGSTR2A.setGstin(jsonFileName);
							EnforcementOfficerGSTR2A enforcementOfficerGSTR2AObj = enforcementOfficerGSTR2ARepository
									.save(enforcementOfficerGSTR2A);

							if (enforcementOfficerGSTR2AObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR7")) {

							EnforcementOfficerGSTR7 enforcementOfficerGSTR7 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR7.class);
							enforcementOfficerGSTR7.setGstin(jsonFileName);
							EnforcementOfficerGSTR7 enforcementOfficerGSTR7Obj = enforcementOfficerGSTR7Repository
									.save(enforcementOfficerGSTR7);

							if (enforcementOfficerGSTR7Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR4")) {

							EnforcementOfficerGSTR4 enforcementOfficerGSTR4 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR4.class);
							enforcementOfficerGSTR4.setGstin(jsonFileName);
							EnforcementOfficerGSTR4 enforcementOfficerGSTR4Obj = enforcementOfficerGSTR4Repository
									.save(enforcementOfficerGSTR4);

							if (enforcementOfficerGSTR4Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR5")) {

							EnforcementOfficerGSTR5 enforcementOfficerGSTR5 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR5.class);
							enforcementOfficerGSTR5.setGstin(jsonFileName);
							EnforcementOfficerGSTR5 enforcementOfficerGSTR5Obj = enforcementOfficerGSTR5Repository
									.save(enforcementOfficerGSTR5);

							if (enforcementOfficerGSTR5Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR6")) {

							EnforcementOfficerGSTR6 enforcementOfficerGSTR6 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR6.class);
							enforcementOfficerGSTR6.setGstin(jsonFileName);
							EnforcementOfficerGSTR6 enforcementOfficerGSTR6Obj = enforcementOfficerGSTR6Repository
									.save(enforcementOfficerGSTR6);

							if (enforcementOfficerGSTR6Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR1SUM")) {

							EnforcementOfficerGSTR1sum enforcementOfficerGSTR1sum = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR1sum.class);
							enforcementOfficerGSTR1sum.setGstin(jsonFileName);
							EnforcementOfficerGSTR1sum enforcementOfficerGSTR1sumObj = enforcementOfficerGSTR1SumRepository
									.save(enforcementOfficerGSTR1sum);

							if (enforcementOfficerGSTR1sumObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR8")) {

							EnforcementOfficerGSTR8 enforcementOfficerGSTR8 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR8.class);
							enforcementOfficerGSTR8.setGstin(jsonFileName);
							EnforcementOfficerGSTR8 enforcementOfficerGSTR8Obj = enforcementOfficerGSTR8Repository
									.save(enforcementOfficerGSTR8);

							if (enforcementOfficerGSTR8Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERGSTR9")) {

							EnforcementOfficerGSTR9 enforcementOfficerGSTR9 = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerGSTR9.class);
							enforcementOfficerGSTR9.setGstin(jsonFileName);
							EnforcementOfficerGSTR9 enforcementOfficerGSTR9Obj = enforcementOfficerGSTR9Repository
									.save(enforcementOfficerGSTR9);

							if (enforcementOfficerGSTR9Obj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERRECORDSEARCHREGISTRATION")) {

							EnforcementOfficerRecordSearchRegistration enforcementOfficerRecordSearchRegistration = objectMapper
									.readValue(new File(jsonFilePath),
											EnforcementOfficerRecordSearchRegistration.class);
							enforcementOfficerRecordSearchRegistration.setGstin(jsonFileName);
							EnforcementOfficerRecordSearchRegistration enforcementOfficerRecordSearchRegistrationObj = enforcementOfficerRecordSearchRegistrationRepository
									.save(enforcementOfficerRecordSearchRegistration);

							if (enforcementOfficerRecordSearchRegistrationObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERRECORDSEARCHPAYMENTS")) {

							EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPayments = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerRecordSearchPayments.class);
							// enforcementOfficerRecordSearchPayments.setGstin(jsonFileName);
							EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPaymentsObj = enforcementOfficerRecordSearchPaymentsRepository
									.save(enforcementOfficerRecordSearchPayments);

							if (enforcementOfficerRecordSearchPaymentsObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else if (application.equalsIgnoreCase("ENFORCEMENTOFFICERRECORDSEARCHRETURNS")) {

							EnforcementOfficerRecordSearchReturns enforcementOfficerRecordSearchReturns = objectMapper
									.readValue(new File(jsonFilePath), EnforcementOfficerRecordSearchReturns.class);
							enforcementOfficerRecordSearchReturns.setGstin(jsonFileName);
							EnforcementOfficerRecordSearchReturns enforcementOfficerRecordSearchReturnsObj = enforcementOfficerRecordSearchReturnsRepository
									.save(enforcementOfficerRecordSearchReturns);

							if (enforcementOfficerRecordSearchReturnsObj.getId() > 0) {
								fileNameEnforcement.setIsProcessed(true);
								fileNameEnforcement.setApplication(application);
								fileNameEnforcementRepository.save(fileNameEnforcement);
								log.info("Updated: {}", jsonFilePath);
							}

						} else {
							log.error("Unknown application type: {}", application);
						}

					} catch (UnrecognizedPropertyException e) {
						log.error("Unrecognized field in JSON file {}: {}", jsonFilePath, e.getMessage());
					} catch (IOException e) {
						log.error("Failed to import data from {}. Error: {}", jsonFilePath, e.getMessage());
					} catch (OutOfMemoryError e) {
						log.error("OutOfMemoryError while processing {}: {}", jsonFilePath, e.getMessage());
						Runtime.getRuntime().gc();
					} catch (Exception e) {
						log.error("Exception while processing {}: {}", jsonFilePath, e.getMessage());
					}

				}

			});
		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
			// Wait for all threads to finish
		}

		return "Data processing completed.";
	}

	////////////////////////////// GSTR3B//////////////////////////////////////////////////////////////////////
	public String getEnforcementGstr3BDetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR3B_SECTIONS);

			List<GstinEnforecementGstr3b> listOfData = gstinEnforecementGstr3bRepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR3B enforcementOfficerGSTR3BTemp = null;
				log.info("Processing EnforcementOfficerGSTR3BTemp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr3b(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR3BTemp = getSectionObjForGstr3b(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR3BTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR3BTemp.getGstin() != null
						&& !enforcementOfficerGSTR3BTemp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr3b(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr3bgetfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr3b(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr3b Gstin = gstinEnforecementGstr3bRepository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);

					if (enforcementOfficerGSTR3BTemp != null && enforcementOfficerGSTR3BTemp.getGstin() != null
							&& !enforcementOfficerGSTR3BTemp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr3bRepository.save(Gstin);
				}
			});
		}
		return "EnforcementOfficerGSTR3BTemp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr3b(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR3BDET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR3B getSectionObjForGstr3b(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR3B enforcementOfficerGSTR3B = new EnforcementOfficerGSTR3B();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR3B = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR3B.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR3B;
	}

	private String getFolderNameForGstr3b(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR3B.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr3bgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR3B.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////////////////// GSTR7//////////////////////////////////////////////////////////////////

	public String getEnforcementGstr7DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr7> listOfData = gstinEnforecementGstr7Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR7 enforcementOfficerGSTR7Temp = null;
				log.info("Processing enforcementOfficerGSTR7Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr7(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR7Temp = getSectionObjForGstr7(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR7Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR7Temp.getTds() != null
						&& enforcementOfficerGSTR7Temp.getTds().getNoRec() > 0) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr7(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr7getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr7(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr7 Gstin = gstinEnforecementGstr7Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR7Temp.getTds() != null
							&& enforcementOfficerGSTR7Temp.getTds().getNoRec() > 0) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr7Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR7Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr7(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR7SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR7 getSectionObjForGstr7(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR7 enforcementOfficerGSTR7 = new EnforcementOfficerGSTR7();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR7 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR7.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR7;
	}

	private String getFolderNameForGstr7(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR7.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr7getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR7.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////////// GSTR2A///////////////////////////////////////////////////////////

	public String getEnforcementGstr2ADetailsAndSave(String username, List<String> sectionList) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR2A_SECTIONS);
			List<GstinEnforecementGstr2A> listOfData = gstinEnforecementGstr2ARepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR2A enforcementOfficerGSTR2ATemp = null;
				for (String section : sectionList) {
					log.info("Processing EnforcementOfficerGSTR2ATemp for GSTIN " + gstinNumber + " for Section "
							+ section + "retPeriod:" + retPeriod);

					Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsGstr2A(gstinNumber,
							retPeriod, section);
					String path = authenticationHelper.getUriWithParam(
							authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

					enforcementOfficerGSTR2ATemp = getSectionObjGstr2A(path, gstinNumber, masterData, apiDetails);
					if (enforcementOfficerGSTR2ATemp == null) {
						log.info("No JSON received for GSTIN " + gstinNumber + " section " + section);
						continue;
					}

					if (section.equalsIgnoreCase("B2B")) {
						if (enforcementOfficerGSTR2ATemp.getB2B() != null
								&& !enforcementOfficerGSTR2ATemp.getB2B().isEmpty()) {
							if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
									getFolderName2A(section, retPeriod), lastDecodedJson)) {
								fileDetailsList.add(enfgstr2agetfileDetails(getFileName(gstinNumber),
										getFolderName2A(section, retPeriod), gstinNumber));
							}
						}
					}
				}
				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr2A Gstin = gstinEnforecementGstr2ARepository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);

					if (enforcementOfficerGSTR2ATemp != null && enforcementOfficerGSTR2ATemp.getB2B() != null
							&& !enforcementOfficerGSTR2ATemp.getB2B().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr2ARepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR2ATemp values Saved";
	}

	private EnforcementOfficerGSTR2A getSectionObjGstr2A(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR2A enforcementOfficerGSTR2A = new EnforcementOfficerGSTR2A();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println(decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR2A = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR2A.class);

			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		}

		else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR2A;
	}

	private Map<String, String> getParamsForGetReturnFileDetailsGstr2A(String gstin, String retPeriod, String section) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR2ADET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		params.put("sec_name", section);
		return params;
	}

	private String getFolderName2A(String section, String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR2A.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\").append(section.toUpperCase()).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr2agetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR2A.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getEnforcementOfficerRecordSearchPaymentsFromGSTNAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_ENFORCEMENT_OFFICER_RECORD_SEARCH_PAYMENTS);

			List<GstinEnforcementRSP> listOfData = gstinEnforcementRSPRepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPaymentsTemp = null;
				log.info("Processing EnforcementOfficerRecordSearchPayments for GSTIN " + gstinNumber + "retPeriod:"
						+ retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForRecordSearchPayment(gstinNumber, retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerRecordSearchPaymentsTemp = downloadAndSaveByGstin(path, gstinNumber, masterData,
						apiDetails);

				if (enforcementOfficerRecordSearchPaymentsTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerRecordSearchPaymentsTemp.getCpins() != null
						&& !enforcementOfficerRecordSearchPaymentsTemp.getCpins().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForRSP(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfrspgetfileDetails(getFileName(gstinNumber),
								getFolderNameForRSP(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforcementRSP Gstin = gstinEnforcementRSPRepository.findFirstByGstinAndRetPeriod(gstinNumber,
						retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerRecordSearchPaymentsTemp.getCpins() != null
							&& !enforcementOfficerRecordSearchPaymentsTemp.getCpins().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforcementRSPRepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerRecordSearchEnforcementTemp values Saved";
	}

	private Map<String, String> getParamsForRecordSearchPayment(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFRECSRCH");
		params.put("module", "PMT");
		params.put("gstin", gstin);
		params.put("fy", retPeriod);
		return params;
	}

	private EnforcementOfficerRecordSearchPayments downloadAndSaveByGstin(String path, String gstinNumber,
			MasterData masterData, APIDetails apiDetails) {
		EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPayments = new EnforcementOfficerRecordSearchPayments();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerRecordSearchPayments = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerRecordSearchPayments.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerRecordSearchPayments;
	}

	private String getFolderNameForRSP(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerRecordSearchPayments.class.getSimpleName().toUpperCase()).append("\\")
				.append(retPeriod).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfrspgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerRecordSearchPayments.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////////////// GSTR4///////////////////////////////////////////
	public String getEnforcementGstr4DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr4> listOfData = gstinEnforecementGstr4Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR4 enforcementOfficerGSTR4Temp = null;
				log.info("Processing enforcementOfficerGSTR4Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr4(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR4Temp = getSectionObjForGstr4(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR4Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR4Temp.getSecsum() != null
						&& !enforcementOfficerGSTR4Temp.getSecsum().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr4(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr4getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr4(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr4 Gstin = gstinEnforecementGstr4Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR4Temp.getSecsum() != null
							&& !enforcementOfficerGSTR4Temp.getSecsum().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr4Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR4Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr4(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR4SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR4 getSectionObjForGstr4(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR4 enforcementOfficerGSTR4 = new EnforcementOfficerGSTR4();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR4 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR4.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR4;
	}

	private String getFolderNameForGstr4(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR4.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr4getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR4.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////////// GSTR5/////////////////////////////////////////////////////////

	public String getEnforcementGstr5DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr5> listOfData = gstinEnforecementGstr5Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR5 enforcementOfficerGSTR5Temp = null;
				log.info("Processing enforcementOfficerGSTR5Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr5(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR5Temp = getSectionObjForGstr5(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR5Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR5Temp.getGstin() != null
						&& !enforcementOfficerGSTR5Temp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr5(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr5getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr5(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr5 Gstin = gstinEnforecementGstr5Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR5Temp.getGstin() != null
							&& !enforcementOfficerGSTR5Temp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr5Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR5Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr5(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR5SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR5 getSectionObjForGstr5(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR5 enforcementOfficerGSTR5 = new EnforcementOfficerGSTR5();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR5 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR5.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR5;
	}

	private String getFolderNameForGstr5(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR5.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr5getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR5.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////////////// GSTR6///////////////////////////////////

	public String getEnforcementGstr6DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr6> listOfData = gstinEnforecementGstr6Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR6 enforcementOfficerGSTR6Temp = null;
				log.info("Processing enforcementOfficerGSTR6Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr6(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR6Temp = getSectionObjForGstr6(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR6Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR6Temp.getGstin() != null
						&& !enforcementOfficerGSTR6Temp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr6(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr6getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr6(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr6 Gstin = gstinEnforecementGstr6Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR6Temp.getGstin() != null
							&& !enforcementOfficerGSTR6Temp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr6Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR6Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr6(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR6SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR6 getSectionObjForGstr6(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR6 enforcementOfficerGSTR6 = new EnforcementOfficerGSTR6();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR6 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR6.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR6;
	}

	private String getFolderNameForGstr6(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR6.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr6getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR6.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	//////////////////////////// GSTR-1Summary/////////////////////////////
	public String getEnforcementGstr1SumDetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr1sum> listOfData = gstinEnforecementGstr1SumRepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR1sum enforcementOfficerGSTR1sumTemp = null;
				log.info("Processing enforcementOfficerGSTR1sumTemp for GSTIN " + gstinNumber + "retPeriod:"
						+ retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr1sum(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR1sumTemp = getSectionObjForGstr1Sum(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR1sumTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR1sumTemp.getGstin() != null
						&& !enforcementOfficerGSTR1sumTemp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr1sum(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr1sumgetfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr1sum(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr1sum Gstin = gstinEnforecementGstr1SumRepository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR1sumTemp.getGstin() != null
							&& !enforcementOfficerGSTR1sumTemp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr1SumRepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR1SumTemp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr1sum(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR1SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR1sum getSectionObjForGstr1Sum(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR1sum enforcementOfficerGSTR1sum = new EnforcementOfficerGSTR1sum();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR1sum = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR1sum.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR1sum;
	}

	private String getFolderNameForGstr1sum(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR1sum.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr1sumgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR1sum.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	//////////////////////// GSTR-8////////////////////////////////

	public String getEnforcementGstr8DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr8> listOfData = gstinEnforecementGstr8Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR8 enforcementOfficerGSTR8Temp = null;
				log.info("Processing enforcementOfficerGSTR8Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr8(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR8Temp = getSectionObjForGstr8(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR8Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR8Temp.getGstin() != null
						&& !enforcementOfficerGSTR8Temp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr8(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr8getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr8(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr8 Gstin = gstinEnforecementGstr8Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR8Temp.getGstin() != null
							&& !enforcementOfficerGSTR8Temp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr8Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR8Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr8(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR8SUM");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR8 getSectionObjForGstr8(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR8 enforcementOfficerGSTR8 = new EnforcementOfficerGSTR8();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR8 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR8.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR8;
	}

	private String getFolderNameForGstr8(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR8.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr8getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR8.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////// GSTR-9////////////////////////////////

	public String getEnforcementGstr9DetailsAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl
					.findByName(Constants.GET_RETURN_FILE_DETAIL_ENFORCEMENT_GSTR7_SECTIONS);

			List<GstinEnforecementGstr9> listOfData = gstinEnforecementGstr9Repository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerGSTR9 enforcementOfficerGSTR9Temp = null;
				log.info("Processing enforcementOfficerGSTR9Temp for GSTIN " + gstinNumber + "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForGetReturnFileDetailsForGstr9(gstinNumber,
						retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerGSTR9Temp = getSectionObjForGstr9(path, gstinNumber, masterData, apiDetails);

				if (enforcementOfficerGSTR9Temp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerGSTR9Temp.getGstin() != null
						&& !enforcementOfficerGSTR9Temp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForGstr9(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfgstr9getfileDetails(getFileName(gstinNumber),
								getFolderNameForGstr9(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforecementGstr9 Gstin = gstinEnforecementGstr9Repository
						.findFirstByGstinAndRetPeriod(gstinNumber, retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerGSTR9Temp.getGstin() != null
							&& !enforcementOfficerGSTR9Temp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforecementGstr9Repository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerGSTR9Temp values Saved";
	}

	private Map<String, String> getParamsForGetReturnFileDetailsForGstr9(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFR9DET");
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		return params;
	}

	private EnforcementOfficerGSTR9 getSectionObjForGstr9(String path, String gstinNumber, MasterData masterData,
			APIDetails apiDetails) {
		EnforcementOfficerGSTR9 enforcementOfficerGSTR9 = new EnforcementOfficerGSTR9();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerGSTR9 = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerGSTR9.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerGSTR9;
	}

	private String getFolderNameForGstr9(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerGSTR9.class.getSimpleName().toUpperCase()).append("\\").append(retPeriod)
				.append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfgstr9getfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerGSTR9.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	//////////////////// EnforcementOfficerRecordSearchEnforcement////////////////////////

	public String getEnforcementOfficerRecordSearchEnforcement(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_ENFORCEMENT_OFFICER_RECORD_SEARCH_PAYMENTS);

			List<GstinEnforcementRSE> listOfData = gstinEnforcementRSERepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerRecordSearchEnforcement enforcementOfficerRecordSearchEnforcementTemp = null;
				log.info("Processing enforcementOfficerRecordSearchEnforcementTemp for GSTIN " + gstinNumber
						+ "retPeriod:" + retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForEnfRecSerchEnforcement(gstinNumber);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerRecordSearchEnforcementTemp = getSectionObjForEnfOffRecSeaEnforcement(path,
						gstinNumber, masterData, apiDetails);

				if (enforcementOfficerRecordSearchEnforcementTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerRecordSearchEnforcementTemp.getGstCases() != null
						&& !enforcementOfficerRecordSearchEnforcementTemp.getGstCases().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForRecordSerEnforcement(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfrecserenforcementgetfileDetails(getFileName(gstinNumber),
								getFolderNameForRecordSerEnforcement(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforcementRSE Gstin = gstinEnforcementRSERepository.findFirstByGstinAndRetPeriod(gstinNumber,
						retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerRecordSearchEnforcementTemp.getGstCases() != null
							&& !enforcementOfficerRecordSearchEnforcementTemp.getGstCases().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforcementRSERepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerRecordSearchEnforcementTemp values Saved";
	}

	private Map<String, String> getParamsForEnfRecSerchEnforcement(String gstin) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFRECSRCH");
		params.put("module", "LIT");
		params.put("gstin", gstin);
		return params;
	}

	private EnforcementOfficerRecordSearchEnforcement getSectionObjForEnfOffRecSeaEnforcement(String path,
			String gstinNumber, MasterData masterData, APIDetails apiDetails) {
		EnforcementOfficerRecordSearchEnforcement enforcementOfficerRecordSearchEnforcement = new EnforcementOfficerRecordSearchEnforcement();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerRecordSearchEnforcement = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerRecordSearchEnforcement.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerRecordSearchEnforcement;
	}

	private String getFolderNameForRecordSerEnforcement(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerRecordSearchEnforcement.class.getSimpleName().toUpperCase()).append("\\")
				.append(retPeriod).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfrecserenforcementgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerRecordSearchEnforcement.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	//////////////////// EnforcementOfficerRecordSearchRegistration////////////////////////

	public String getEnforcementOfficerRecordSearchRegistration(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_ENFORCEMENT_OFFICER_RECORD_SEARCH_PAYMENTS);

			List<GstinEnforcementRSRegis> listOfData = gstinEnforcementRSRegisRepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				// String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerRecordSearchRegistration enforcementOfficerRecordSearchRegistrationTemp = null;
				log.info("Processing enforcementOfficerRecordSearchRegistrationTemp for GSTIN " + gstinNumber
				// + "retPeriod:" + retPeriod
				);

				Map<String, String> paramsForFileDetails = getParamsForEnfRecSerchRegisteration(gstinNumber);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerRecordSearchRegistrationTemp = getSectionObjForEnfOffRecSeaRegisteration(path,
						gstinNumber, masterData, apiDetails);

				if (enforcementOfficerRecordSearchRegistrationTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerRecordSearchRegistrationTemp.getGstin() != null
						&& !enforcementOfficerRecordSearchRegistrationTemp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForRecordSerRegisteration(), lastDecodedJson)) {
						fileDetailsList.add(enfrecserregisterationgetfileDetails(getFileName(gstinNumber),
								getFolderNameForRecordSerRegisteration(), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforcementRSRegis Gstin = gstinEnforcementRSRegisRepository.findFirstByGstin(gstinNumber);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerRecordSearchRegistrationTemp.getGstin() != null
							&& !enforcementOfficerRecordSearchRegistrationTemp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforcementRSRegisRepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerRecordSearchRegistrationTemp values Saved";
	}

	private Map<String, String> getParamsForEnfRecSerchRegisteration(String gstin) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFRECSRCH");
		params.put("module", "REG");
		params.put("gstin", gstin);
		return params;
	}

	private EnforcementOfficerRecordSearchRegistration getSectionObjForEnfOffRecSeaRegisteration(String path,
			String gstinNumber, MasterData masterData, APIDetails apiDetails) {
		EnforcementOfficerRecordSearchRegistration enforcementOfficerRecordSearchRegistration = new EnforcementOfficerRecordSearchRegistration();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerRecordSearchRegistration = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerRecordSearchRegistration.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerRecordSearchRegistration;
	}

	private String getFolderNameForRecordSerRegisteration() {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerRecordSearchRegistration.class.getSimpleName().toUpperCase()).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfrecserregisterationgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();
		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerRecordSearchRegistration.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

	////////////////////////// EnforcementOfficerRecordSearchReturn////////////////////////

	public String getEnforcementOfficerRecordSearchReturnsFromGSTNAndSave(String username) {
		MasterData masterData = masterDataService.getMasterdatabyName(username);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl.findByName(Constants.GET_ENFORCEMENT_OFFICER_RECORD_SEARCH_PAYMENTS);

			List<GstinEnforcementRSR> listOfData = gstinEnforcementRSRRepository
					.findAllByIsProcessedFalseAndFoundInfoTrueOrderById();
			listOfData.forEach(gstinEntry -> {
				String gstinNumber = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();
				List<FileNameEnforcement> fileDetailsList = new ArrayList<>();
				EnforcementOfficerRecordSearchReturns enforcementOfficerRecordSearchReturnsTemp = null;
				log.info("Processing EnforcementOfficerRecordSearchReturns for GSTIN " + gstinNumber + "retPeriod:"
						+ retPeriod);

				Map<String, String> paramsForFileDetails = getParamsForRecordSearchReturns(gstinNumber, retPeriod);
				String path = authenticationHelper.getUriWithParam(
						authenticationHelper.getFullPath(masterData, apiDetails), paramsForFileDetails);

				enforcementOfficerRecordSearchReturnsTemp = ReturndownloadAndSaveByGstin(path, gstinNumber, masterData,
						apiDetails);

				if (enforcementOfficerRecordSearchReturnsTemp == null) {
					log.info("No JSON received for GSTIN " + gstinNumber);
					return;
				}

				if (enforcementOfficerRecordSearchReturnsTemp.getGstin() != null
						&& !enforcementOfficerRecordSearchReturnsTemp.getGstin().isEmpty()) {
					if (FileDownloadHelperForEnforcement.createFilesInFolder(getFileName(gstinNumber),
							getFolderNameForRSR(retPeriod), lastDecodedJson)) {
						fileDetailsList.add(enfrsrgetfileDetails(getFileName(gstinNumber),
								getFolderNameForRSR(retPeriod), gstinNumber));
					}
				}

				if (!fileDetailsList.isEmpty()) {
					fileDetaisServiceImpl.createAll(fileDetailsList);
				}

				GstinEnforcementRSR Gstin = gstinEnforcementRSRRepository.findFirstByGstinAndRetPeriod(gstinNumber,
						retPeriod);

				if (Gstin != null) {
					Gstin.setIsProcessed(true);
					if (enforcementOfficerRecordSearchReturnsTemp.getGstin() != null
							&& !enforcementOfficerRecordSearchReturnsTemp.getGstin().isEmpty()) {
						Gstin.setIsProcessed(true);
					} else {
						Gstin.setFoundInfo(false);
					}

					gstinEnforcementRSRRepository.save(Gstin);
				}

			});
		}
		return "EnforcementOfficerRecordSearchReturnsTemp values Saved";
	}

	private Map<String, String> getParamsForRecordSearchReturns(String gstin, String retPeriod) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "ENFRECSRCH");
		params.put("module", "RET");
		params.put("gstin", gstin);
		params.put("fy", retPeriod);
		return params;
	}

	private EnforcementOfficerRecordSearchReturns ReturndownloadAndSaveByGstin(String path, String gstinNumber,
			MasterData masterData, APIDetails apiDetails) {
		EnforcementOfficerRecordSearchReturns enforcementOfficerRecordSearchReturns = new EnforcementOfficerRecordSearchReturns();

		GSTUserSession gstUserSession = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());
		HttpHeaders headers = authenticationHelper.getDefaultHeadersEnforcement(masterData,
				gstUserSession.getAuthToken(), apiDetails.getApiContentType());

		GSTCommonResponseBean responseEntity = restClient.get(path, GSTCommonResponseBean.class, headers);
		if (responseEntity.status_cd.equals("1")) {
			try {
				byte[] decodedData = Base64.getDecoder().decode(responseEntity.getData());
				String decodedDataString = new String(decodedData, "UTF-8");
				System.out.println("jsonData::" + decodedDataString);
				lastDecodedJson = decodedDataString;
				enforcementOfficerRecordSearchReturns = new ObjectMapper().readValue(decodedDataString,
						EnforcementOfficerRecordSearchReturns.class);
			} catch (Exception e) {
				log.error("JSON Parsing Exception for GSTIN " + gstinNumber + " cause " + e.getMessage());
			}
		} else if (responseEntity.status_cd.equals("0") && !(responseEntity.getError().isEmpty())
				&& responseEntity.getError().get("message") != null) {
			if (responseEntity.getError().get("message")
					.equalsIgnoreCase("No document found for the provided Inputs")) {
				log.error("No Data Found For GSTIN User " + gstinNumber);
			}
		} else {
			log.error("responseEntity " + responseEntity);
			log.error("No Data Found For GSTIN User " + gstinNumber + " from GSTIN server");
		}
		return enforcementOfficerRecordSearchReturns;
	}

	private String getFolderNameForRSR(String retPeriod) {
		StringBuilder path = new StringBuilder(logbackConfig.getFileDirEnforcement())
				.append(EnforcementOfficerRecordSearchReturns.class.getSimpleName().toUpperCase()).append("\\")
				.append(retPeriod).append("\\");
		return path.toString();
	}

	private FileNameEnforcement enfrsrgetfileDetails(String fileName, String folderName, String gstin) {
		FileNameEnforcement fileDetails = new FileNameEnforcement();

		fileDetails.setIsProcessed(false);
		fileDetails.setFileName(fileName);
		fileDetails.setFilePath(folderName + fileName);
		fileDetails.setGstin(gstin);
		fileDetails.setApplication(EnforcementOfficerRecordSearchReturns.class.getSimpleName().toUpperCase());
		return fileDetails;
	}

}
