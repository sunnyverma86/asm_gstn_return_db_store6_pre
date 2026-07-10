package com.deloitte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.GSTAuthenticationInputBean;
import com.deloitte.common.bean.GSTAuthenticationResponseBean;
import com.deloitte.common.constant.Constants;
import com.deloitte.common.entity.APIDetails;
import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.repository.common.GSTUserSessionRepository;
import com.deloitte.returns.service.GstUserSessionServicesSupport;
import com.deloitte.service.support.AESEncryption;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GSTUserSessionServicesImpl {

	@Autowired
	private APIDetailsImpl apiDetailsImpl;

	@Autowired
	private GSTUserSessionRepository gSTUserSessionRepository;

	@Autowired
	private MasterDataServiceImpl masterDataServiceImpl;

	@Autowired
	private GstUserSessionServicesSupport gstUserSessionServicesSupport;

	public GSTUserSession createUserSession(String username) {
		GSTUserSession gSTUserSession = null;
		APIDetails apiDetails = apiDetailsImpl.findByName(Constants.COMMON_AUTH);
		if (apiDetails != null) {
			gSTUserSession = gSTUserSessionRepository.save(getUserSession(username, apiDetails));
		}
		return gSTUserSession;
	}

	public GSTUserSession getUserSession(String username, APIDetails apiDetails) {

		GSTUserSession gstUserSession = new GSTUserSession();
		gstUserSession.setUserName(username);

		MasterData masterData = masterDataServiceImpl.getMasterdatabyName(username);

		if (masterData == null) {
			log.error("User {} not found in Master Data Table", username);
			throw new IllegalStateException("User not found: " + username);
		}

		String appKey = AESEncryption.getAppkey();
		gstUserSession.setAppKey(appKey);

		String encryAppKey = AESEncryption.getEncryptedAppKey(appKey);
		String encryptedPassword = AESEncryption.getEncryptedPassword(masterData.getPassword());

		log.debug("AppKey :{}", appKey);
		log.debug("Encrypted AppKey :{}", encryAppKey);
		log.debug("Encrypted Password :{}", encryptedPassword);

		createRequestBodyForAuth(encryAppKey, encryptedPassword, gstUserSession, masterData, apiDetails);

		return gstUserSession;
	}

	private void createRequestBodyForAuthOld(String appKey, String encryptedPassword, GSTUserSession gSTUserSession,
			MasterData masterData, APIDetails apiDetails) {
		GSTAuthenticationInputBean gstAuthenticationInputBean = new GSTAuthenticationInputBean();
		gstAuthenticationInputBean.setAction(apiDetails.getApiAction());
		gstAuthenticationInputBean.setUsername(gSTUserSession.getUserName());
		gstAuthenticationInputBean.setPassword(encryptedPassword);
		gstAuthenticationInputBean.setAppKey(appKey);
		GSTAuthenticationResponseBean gstAuthenticationResponseBean = gstUserSessionServicesSupport.doAuth(apiDetails,
				gstAuthenticationInputBean, masterData);

		if (null != gstAuthenticationResponseBean && gstAuthenticationResponseBean.getStatus_cd().equals("0")) {
			log.error("Exception thrown from GSTN server while authenticating user session "
					+ gstAuthenticationResponseBean.getError().get("message"));
		} else {
			assert gstAuthenticationResponseBean != null;
			gSTUserSession.setSek(gstAuthenticationResponseBean.getSek());
			gSTUserSession.setAuthToken(gstAuthenticationResponseBean.getAuth_token());
			gSTUserSessionRepository.save(gSTUserSession);
		}

	}

	private void createRequestBodyForAuth(String appKey, String encryptedPassword, GSTUserSession gSTUserSession,
			MasterData masterData, APIDetails apiDetails) {

		GSTAuthenticationInputBean gstAuthenticationInputBean = new GSTAuthenticationInputBean();
		gstAuthenticationInputBean.setAction(apiDetails.getApiAction());
		gstAuthenticationInputBean.setUsername(gSTUserSession.getUserName());
		gstAuthenticationInputBean.setPassword(encryptedPassword);
		gstAuthenticationInputBean.setAppKey(appKey);

		GSTAuthenticationResponseBean response = null;

		for (int attempt = 1; attempt <= 10; attempt++) {

			log.info("Authentication Attempt : {}", attempt);

			response = gstUserSessionServicesSupport.doAuth(apiDetails, gstAuthenticationInputBean, masterData);

			// Success
			if (response != null && !"0".equals(response.getStatus_cd())) {

				log.info("Authentication Successful on Attempt {}", attempt);

				gSTUserSession.setSek(response.getSek());
				gSTUserSession.setAuthToken(response.getAuth_token());

				gSTUserSessionRepository.save(gSTUserSession);

				return;
			}

			// Failure
			if (response != null && response.getError() != null) {
				log.error("Attempt {} Failed : {}", attempt, response.getError().get("message"));
			} else {
				log.error("Attempt {} Failed : No Response from GST", attempt);
			}
			try {
				Thread.sleep(1000); // 1 second
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		throw new RuntimeException("GST Authentication failed after 10 attempts.");
	}

}
