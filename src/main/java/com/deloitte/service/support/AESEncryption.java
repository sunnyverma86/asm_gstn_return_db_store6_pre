package com.deloitte.service.support;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class AESEncryption {

	public static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
	public static final String AES_ALGORITHM = "AES";
	public static final int ENC_BITS = 256;

	private static Cipher ENCRYPT_CIPHER;
	private static Cipher DECRYPT_CIPHER;
	private static KeyGenerator KEYGEN;

	static {
		try {
			ENCRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
			DECRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
			KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
			KEYGEN.init(ENC_BITS);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException("Error initializing AES encryption.", e);
		}
	}

	// Encode bytes to Base64 string
	private static String encodeBase64String(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	// Decode Base64 string to bytes
	private static byte[] decodeBase64StringToByte(String stringData) {
		return Base64.getDecoder().decode(stringData);
	}

	// Generate a secure AES key
	private static String generateSecureKey() throws Exception {
		SecretKey secretKey = KEYGEN.generateKey();
		return encodeBase64String(secretKey.getEncoded());
	}

	// Encrypt the text using the provided secret key
//	private static String encryptEK(byte[] plainText, byte[] secret) {
//		try {
//			SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
//			ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
//			byte[] encrypted = ENCRYPT_CIPHER.doFinal(plainText);
//			return encodeBase64String(encrypted);
//		} catch (Exception e) {
//			throw new RuntimeException("Encryption failed", e);
//		}
//	}

	// Decrypt the text using the provided secret key
	public static byte[] decrypt(String encryptedText, byte[] secret) throws Exception {
		SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
		DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
		byte[] decoded = decodeBase64StringToByte(encryptedText);
		return DECRYPT_CIPHER.doFinal(decoded);
	}

	// Generate the application key (app key) for AES encryption
	public static String getAppkey() {
		try {
			return generateSecureKey();
		} catch (Exception e) {
			throw new RuntimeException("Failed to generate app key.", e);
		}
	}

	// Encrypt the app key
	public static String getEncryptedAppKey(String appKey) {
		String encryptedAppkey = Strings.EMPTY;
		try {
			byte[] decodedAppKey = decodeBase64StringToByte(appKey);
			encryptedAppkey = EncryptionUtil.generateEncreyptedkey(decodedAppKey);
		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt app key.", e);
		}
		return encryptedAppkey;
	}

	// Encrypt the password
	public static String getEncryptedPassword(String password) {
		String encryptedPasswordKey = Strings.EMPTY;
		try {
			encryptedPasswordKey = EncryptionUtil.generateEncreyptedkey(password.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt password.", e);
		}
		return encryptedPasswordKey;
	}

	// Base64 decode a string
	public static String baseDecode(String data) {
		return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
	}
}
