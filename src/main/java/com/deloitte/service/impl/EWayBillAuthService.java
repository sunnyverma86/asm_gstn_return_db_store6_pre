package com.deloitte.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.deloitte.returns.entity.AEwayBill.EWayBillAuthBean;
import com.deloitte.returns.repository.eway.EWayBillBeanRepository;

import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Log4j2
public class EWayBillAuthService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${clientId}")
	private String clientId;

	@Value("${clientSecret}")
	private String clientSecret;

	@Value("${stateCode}")
	private String stateCode;

	@Value("${userNames}")
	private String userNames;

	@Value("${password}")
	private String password;

	@Value("${pubKey}")
	private String publicallyKey;

	@Value("${encAppKey}")
	private String encAppKey;

	@Value("${url}")
	private String url;

	static byte[] appKey = null;

	@Autowired
	private EWayBillBeanRepository eWayBillBeanRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public EWayBillAuthBean authenticateAndProcess() throws UnsupportedEncodingException {

		log.info("Starting E-Way Bill authentication process");

		EWayBillAuthBean authenticationBean = new EWayBillAuthBean();
		String passwordMD5 = null;

		try {
			passwordMD5 = generateMD5();
			log.debug("Password MD5 generated successfully");
		} catch (NoSuchAlgorithmException e) {
			log.error("Error generating MD5 password", e);
			throw new RuntimeException("Failed to generate MD5 password", e);
		}

		encAppKey = getEncryptedAESKey();
		log.debug("Encrypted App Key generated successfully");

		String payload = "{\n" + "\"action\":\"ACCESSTOKEN\",\n" + "\"username\":\"" + userNames + "\",\n"
				+ "\"password\":\"" + passwordMD5 + "\",\n" + "\"app_key\":\"" + encAppKey + "\"\n" + "}";

		log.debug("Authentication request payload prepared for user: {}", userNames);

		HttpHeaders headers = createHeaders();
		HttpEntity<String> request = new HttpEntity<>(payload, headers);

		log.info("Calling GST E-Way Bill authentication API. URL={}", url);

		// ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
		// request, String.class);
		// 1️⃣ Log request details before sending
		log.info("==============================================");
		log.info("▶ HTTP Request Details");
		log.info("Method : POST");
		log.info("URL    : {}", url);

		// Use headers from the existing HttpEntity
		HttpHeaders requestHeaders = request.getHeaders();
		log.info("Headers:");
		requestHeaders.forEach((key, values) -> log.info("  {}: {}", key, values));

		String body = request.getBody();
		log.info("Body   : {}", body != null ? body : "null");
		log.info("==============================================");

		// 2️⃣ Make the request (line stays the same)
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		// 3️⃣ Log response details
		log.info("▶ HTTP Response Details");
		log.info("Status Code : {}", response.getStatusCode().value());

		HttpHeaders respHeaders = response.getHeaders();
		log.info("Headers     :");
		respHeaders.forEach((key, values) -> log.info("  {}: {}", key, values));

		String respBody = response.getBody();
		log.info("Body        : {}", respBody != null ? respBody : "null");
		log.info("==============================================");
//new changes

		log.info("Authentication API response received. HTTP Status={}", response.getStatusCode().value());

		if (response.getStatusCode().value() != 200) {
			log.error("Authentication API failed. HTTP Status={}", response.getStatusCode().value());
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode().value());
		}

		String responseText = response.getBody();
		log.debug("Authentication API response body: {}", responseText);

		JsonNode rootNode = objectMapper.readTree(responseText);
		String status = rootNode.get("status").asText();

		log.info("Authentication API status: {}", status);

		if ("0".equals(status)) {
			String errorMsg = new String(Base64.getDecoder().decode(rootNode.get("error").asText()), "utf-8");
			authenticationBean.setErrorDesc(errorMsg);

			log.error("Authentication failed. Error={}", errorMsg);
			return authenticationBean;

		} else if ("1".equals(status)) {

			authenticationBean.setAuthtoken(rootNode.get("authtoken").asText());
			authenticationBean.setSek(rootNode.get("sek").asText());
			authenticationBean.setEncSek(rootNode.get("sek").asText());
			authenticationBean.setStatus(status);

			log.debug("Auth token and SEK received successfully");

			String sek = decrptBySymmetricKeySEK(authenticationBean.getEncSek());
			authenticationBean.setAppKeyDb(appKey);

			eWayBillBeanRepository.save(authenticationBean);

			log.info("Authentication successful. Token saved in DB for user={}", userNames);
		}

		log.info("E-Way Bill authentication process completed");

		return authenticationBean;
	}

	private HttpHeaders createHeaders() {
		log.debug("Creating HTTP headers for E-Way Bill authentication request");

		HttpHeaders headers = new HttpHeaders();
		headers.set("client-id", clientId);
		headers.set("client-secret", clientSecret);
		headers.set("statecode", stateCode);
		headers.set("Content-Type", "application/json");

		log.info("HTTP headers created successfully for statecode={}", stateCode);

		return headers;
	}

	private String generateMD5() throws NoSuchAlgorithmException {// mp
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] byteData = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : byteData) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		String passwordMD5 = sb.toString();
		String encPasswordMD5 = null;
		try {
			encPasswordMD5 = encryptAsymmetricKey(passwordMD5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encPasswordMD5;
	}

	private String encryptAsymmetricKey(String clearText) throws Exception {// mp
		PublicKey publicKey = getPublicKeyOne();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedText = cipher.doFinal(clearText.getBytes());
		return Base64.getEncoder().encodeToString(encryptedText);
	}

	public PublicKey getPublicKeyOne() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		String pubKey = publicallyKey;// mp
		byte[] decodedKey = Base64.getDecoder().decode(pubKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(spec);
	}

	public static String decrptBySymmetricKeySEK(String encryptedSek) {
		Key aesKey = new SecretKeySpec(appKey, "AES"); // converts bytes(32 byte random generated) to key
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // encryption type = AES with padding PKCS5
			cipher.init(Cipher.DECRYPT_MODE, aesKey); // initiate decryption type with the key
			byte[] encryptedSekBytes = Base64.getDecoder().decode(encryptedSek); // decode the base64 encryptedSek to
			System.out.println("encryptedSekBytes:::" + encryptedSekBytes); // bytes
			byte[] decryptedSekBytes = cipher.doFinal(encryptedSekBytes); // decrypt the encryptedSek with the
																			// initialized cipher containing the
																			// key(Results in bytes)
			byte[] sekBytes = decryptedSekBytes;
			String decryptedSek = Base64.getEncoder().encodeToString(decryptedSekBytes); // convert the
																							// decryptedSek(bytes) to
																							// Base64 String
			return decryptedSek; // return results in base64 string
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedSek;
	}

	public static String getEncryptedAESKey() {
		String encryptedAESKey = "";
		byte[] aesKey = createAESKey();
		try {
			encryptedAESKey = encryptAsymmentricKey(aesKey);
		} catch (Exception ex) {

		}
		return encryptedAESKey;
	}

	public static byte[] createAESKey() {

		try {
			KeyGenerator gen = KeyGenerator.getInstance("AES");
			gen.init(128);
			SecretKey secret = gen.generateKey();
			appKey = secret.getEncoded();
		} catch (NoSuchAlgorithmException ex) {

		}
		return appKey;
	}

	private static String encryptAsymmentricKey(byte[] clearTextByteArray) throws Exception {
		PublicKey publicKeys = getPublicKey();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, publicKeys);
		byte[] encryptedText = cipher.doFinal(clearTextByteArray);
		String encryptedPassword = Base64.getEncoder().encodeToString(encryptedText);
		return encryptedPassword;
	}

	public static PublicKey getPublicKey()
			throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

		String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2PkhjvWu+lDEv/ane+uVN44MAZBhWn2Xbr5zEu7h9LpXJXhrwKYhtvWR6YmjAR4AcXDwA3P74Hjc8/jsW92Q5B4ddXJrRbsU3lac1GhjCNma31FlW7Mpjr5eqPNTuImJr1WgDR9iRuCFYt4enRkvfywdnDa++QK6fdjS4/kssJxlEXBtlXKoFuSBGjbf0JA56qHo8yqXoYoqgl9Z7e9X8GZv6soB1JDH9dxMmaqEwsxaonDG+8NdR2RcYeJAnx2s/PBokpbCVPCQiSD5mmYWSZePF7L4mqvYS7ByrIj1cBH8qq6TafTcLkrr/TiZjpAADPwuynQDE120BpFaqIEqlQIDAQAB";

		byte[] keyBytes = Base64.getDecoder().decode(pubKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}

}
