package com.deloitte.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTCommonResponseBean;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.GstinEntity;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.entity.Gstr2a.FileDetails;
import com.deloitte.returns.entity.Gstr2a.FileNameGstr2a;
import com.deloitte.returns.entity.Gstr2a.Gstr2a;
import com.deloitte.service.abs.AbstractServiceClassForAll;
import com.deloitte.service.support.AESEncryption;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Gstr2aServiceImpl extends AbstractServiceClassForAll {

	private static final int MAX_RETRY = 3;
	private static final int FAILURE_THRESHOLD = 10;
	protected static final int BATCH_SIZE = 150; // Adjust the batch size as needed
	protected static final int THREAD_POOL_SIZE = 15; // Adjust the number of threads as needed

	public String getGstr2aDetailsAndSave(String username, List<String> sectionList) {

		log.info("🚀 GSTR2A Job Started | User: {} | Sections: {}", username, sectionList);

		int retryCount = 0;
		boolean retryRequired = true;

		while (retryRequired && retryCount < MAX_RETRY) {

			retryCount++;
			int failureCount = 0;

			log.info("🔁 Attempt: {}", retryCount);

			MasterData masterData = masterDataService.getMasterdatabyName(username);
			if (masterData == null) {
				log.error("❌ MasterData Not Found for user {}", username);
				return "Invalid Username";
			}

			APIDetails apiDetails = apiDetailsImpl.findByName("Get GSTR2A Details");
			List<GstinEntity> gstinList = gstinRepository.findAllByIsProcessedGstr2aFalseAndFoundInfoTrueOrderById();

			for (GstinEntity gstinEntry : gstinList) {

				boolean isAnySectionFound = false;
				List<FileDetails> fileDetailsList = new ArrayList<>();

				String gstin = gstinEntry.getGstin();
				String retPeriod = gstinEntry.getRetPeriod();

				log.info("▶ Processing GSTIN: {} | Period: {}", gstin, retPeriod);

				for (String section : sectionList) {
					boolean found = processSectionData(apiDetails, masterData, gstin, retPeriod, section,
							fileDetailsList);
					if (found)
						isAnySectionFound = true;
				}

				if (!fileDetailsList.isEmpty()) {
					createAllManual(fileDetailsList);
				}

				GstinEntity gstinDb = gstinRepository.findFirstByGstinAndRetPeriod(gstin, retPeriod);

				if (isAnySectionFound) {
					gstinDb.setIsProcessedGstr2a(true);
					gstinDb.setFoundInfo(true);
				} else {
					gstinDb.setFoundInfo(false);
					failureCount++;
				}

				saveManual(gstinDb);
			}

			log.info("📊 Attempt {} Completed | Failure Count: {}", retryCount, failureCount);

			retryRequired = failureCount > FAILURE_THRESHOLD;

			if (retryRequired) {
				log.warn("⚠ Failure count {} exceeded threshold {} → Retrying full job", failureCount,
						FAILURE_THRESHOLD);
			}
		}

		log.info("✅ GSTR2A Job Completed Successfully");
		return "GSTR2A values Saved Successfully";
	}

	private boolean processSectionData(APIDetails apiDetails, MasterData masterData, String gstin, String retPeriod,
			String section, List<FileDetails> fileDetailsList) {

		Map<String, String> params = getParams(apiDetails, masterData, gstin, section, retPeriod);

		String path = authenticationHelper.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails),
				params);

		log.debug("Calling API | GSTIN:{} | Section:{} | Path:{}", gstin, section, path);

		Gstr2a data = getSectionObj(path, gstin, masterData, apiDetails);

		boolean hasData = checkSectionData(section, data);

		if (hasData) {
			if (fileDownloadHelperForGstr2a.createFilesInFolder(getFileName(gstin), getFolderName(section, retPeriod),
					data)) {

				fileDetailsList.add(getfileDetails(getFileName(gstin), getFolderName(section, retPeriod), gstin));
			}
		}

		return hasData;
	}

	private Map<String, String> getParams(APIDetails apiDetails, MasterData masterData, String gstin, String section,
			String retPeriod) {

		Map<String, String> params = new HashMap<>();
		params.put("action", apiDetails.getApiAction());
		params.put("gstin", gstin);
		params.put("ret_period", retPeriod);
		params.put("sec_name", section);
		return params;
	}

	private boolean checkSectionData(String section, Gstr2a g) {
		return switch (section.toUpperCase()) {
		case "B2B" -> !g.getGstr2aB2B().isEmpty();
		case "B2BA" -> !g.getGstr2aB2Ba().isEmpty();
		case "CDN" -> !g.getGstr2aCdn().isEmpty();
		case "ISD" -> !g.getGstr2aIsd().isEmpty();
		default -> false;
		};
	}

	private FileDetails getfileDetails(String fileName, String folderName, String gstin) {
		FileDetails f = new FileDetails();
		f.setFileName(fileName);
		f.setFilePath(folderName + fileName);
		f.setGstin(gstin);
		f.setType(Gstr2a.class.getSimpleName().toUpperCase());
		return f;
	}

	private String getFileName(String gstin) {
		return gstin + ".json";
	}

	private String getFolderName(String section, String retPeriod) {
		return logbackConfig.getFileDir() + "GSTR2A\\" + retPeriod + "\\" + section + "\\";
	}

	public void createAllManual(List<FileDetails> fileDetails) {
		fileDetailsRepository.saveAllAndFlush(fileDetails);
	}

	private String saveManual(GstinEntity gstin) {
		gstinRepository.save(gstin);
		return "GSTIN Saved";
	}

	private Gstr2a getSectionObj(String path, String gstin, MasterData masterData, APIDetails apiDetails) {

		Gstr2a gstr2a = new Gstr2a();

		try {
			GSTUserSession session = gstUserSessionServices.getUserSessionsByName(masterData.getUserName());

			HttpHeaders headers = authenticationHelper.getDefaultHeaders(masterData, session.getAuthToken(),
					apiDetails.getApiContentType());

			GSTCommonResponseBean response = restClient.get(path, GSTCommonResponseBean.class, headers);

			if ("1".equals(response.status_cd)) {
				gstr2a = new ObjectMapper().readValue(AESEncryption.baseDecode(response.getData()), Gstr2a.class);
			}

		} catch (Exception e) {
			log.error("🔥 API Processing Error | GSTIN:{} | {}", gstin, e.getMessage(), e);
		}

		return gstr2a;
	}

	public String saveDataFromJsonToDb() throws IOException {

		log.info("🚀 JSON → DB Import Started");

		ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		ObjectMapper mapper = new ObjectMapper();

		List<FileNameGstr2a> files = fileNameGstr2aRepository.findAllByIsProcessedFalseOrderById();

		files.forEach(file -> executor.submit(() -> {

			try {
				Gstr2a g = mapper.readValue(new File(file.getFilePath()), Gstr2a.class);

				String gstin = file.getFileName().replace(".json", "");
				g.setGstin(gstin);

				gstr2aRepository.save(g);

				file.setIsProcessed(true);
				fileNameGstr2aRepository.save(file);

				log.info("✅ Imported {}", file.getFileName());

			} catch (Exception e) {
				log.error("❌ Import Failed {}", file.getFilePath(), e);
			}
		}));

		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		return "JSON Import Completed Successfully";
	}
	//

	public String getCommonGstr2aDetailsAndSave(String userName, List<String> sectionList, String gstinNumber,
			String retPeriod) {
		List<Gstr2a> gstr2aList = new ArrayList<Gstr2a>();
		MasterData masterData = masterDataService.getMasterdatabyName(userName);
		if (null != masterData) {
			APIDetails apiDetails = apiDetailsImpl.findByName("Get GSTR2A Details");

			List<FileDetails> fileDetailsList = new ArrayList<>();

			for (String section : sectionList) {
				log.info("Processing date:" + retPeriod + "  GSTIN " + gstinNumber + " Period " + retPeriod
						+ " for Section " + section);
				Map<String, String> params = getParams(apiDetails, masterData, gstinNumber, section, retPeriod);
				String path = authenticationHelper
						.getUriWithParam(authenticationHelper.getFullPath(masterData, apiDetails), params);

				if (section.equalsIgnoreCase("B2B")) {
					Gstr2a gstr2aTemp = getSectionObj(path, gstinNumber, masterData, apiDetails);
					if (!gstr2aTemp.getGstr2aB2B().isEmpty()) {
						if (fileDownloadHelperForGstr2a.createFilesInFolder(getFileName(gstinNumber),
								getFolderName(section, retPeriod), gstr2aTemp)) {
							fileDetailsList.add(getfileDetails(getFileName(gstinNumber),
									getFolderName(section, retPeriod), gstinNumber));
						}
					}
				}
				if (section.equalsIgnoreCase("B2BA")) {
					Gstr2a gstr2aTemp = getSectionObj(path, gstinNumber, masterData, apiDetails);
					if (!gstr2aTemp.getGstr2aB2Ba().isEmpty()) {
						if (fileDownloadHelperForGstr2a.createFilesInFolder(getFileName(gstinNumber),
								getFolderName(section, retPeriod), gstr2aTemp)) {
							fileDetailsList.add(getfileDetails(getFileName(gstinNumber),
									getFolderName(section, retPeriod), gstinNumber));
						}
					}
				}
				if (section.equalsIgnoreCase("CDN")) {
					Gstr2a gstr2aTemp = getSectionObj(path, gstinNumber, masterData, apiDetails);
					if (!gstr2aTemp.getGstr2aCdn().isEmpty()) {
						if (fileDownloadHelperForGstr2a.createFilesInFolder(getFileName(gstinNumber),
								getFolderName(section, retPeriod), gstr2aTemp)) {
							fileDetailsList.add(getfileDetails(getFileName(gstinNumber),
									getFolderName(section, retPeriod), gstinNumber));
						}
					}
				}
				if (section.equalsIgnoreCase("ISD")) {
					Gstr2a gstr2aTemp = getSectionObj(path, gstinNumber, masterData, apiDetails);
					if (!gstr2aTemp.getGstr2aIsd().isEmpty()) {
						if (fileDownloadHelperForGstr2a.createFilesInFolder(getFileName(gstinNumber),
								getFolderName(section, retPeriod), gstr2aTemp)) {
							fileDetailsList.add(getfileDetails(getFileName(gstinNumber),
									getFolderName(section, retPeriod), gstinNumber));
						}
					}
				}

			}
			if (!fileDetailsList.isEmpty()) {
				createAllManual(fileDetailsList);
				return "data import and into the drive Period:" + retPeriod + " ,GSTIN Number{}:" + gstinNumber;
			}
			// }
		}
		return "Data not import in  Period:" + retPeriod + " ,GSTIN Number{}:" + gstinNumber;
	}

}
