package com.deloitte.service.support;

import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EncryptionUtil {

	@Value("${api.development.file.location}")
	private String apiDevelopmentFileLocation;

	public static String publicKeyUrl1;

	@PostConstruct
	public void init() {
		publicKeyUrl1 = apiDevelopmentFileLocation;
		System.out.println("Loaded cert path: " + publicKeyUrl1);
	}

	//

	// private static String file;

	private static PublicKey readPublicKey(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);
		PublicKey pk = certificate.getPublicKey();
		return pk;

	}

	/**
	 * This method is used to encrypt the string , passed to it using a public key
	 * provided
	 * 
	 * @param planTextToEncrypt : Text to encrypt
	 * @return :encrypted string
	 */
	public static String encrypt(byte[] plaintext) throws Exception, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		System.out.println("publicKeyUrl1:  " + publicKeyUrl1);
		PublicKey key = readPublicKey(publicKeyUrl1);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByte = cipher.doFinal(plaintext);
		String encodedString = new String(java.util.Base64.getEncoder().encode(encryptedByte));
		return encodedString;
	}

	public static String generateEncreyptedkey(byte[] key) {
		try {
			return encrypt(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
