package com.deloitte.service.utility;


import org.springframework.stereotype.Component;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.Charsets;

import com.google.common.io.CharStreams;

@Component
public class GstUtil {

	private static final String AES_ALGORITHM = "AES";
	private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static final String CHARACTER_ENCODING = "UTF-8";
	private static final int ENC_BITS = 256;
	private static KeyGenerator KEYGEN;
	private static final String PUBLIC_KEY_PATH_PEM = "gstnpublic_1.pem";
	private static final String RSA_ALGORITHM = "RSA";
	private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	private static GstUtil obj = new GstUtil();

	/**
	 * This method is used to decode the base64 encoded string to byte[]
	 *
	 * @param stringData : String to decode
	 * @return : decoded String
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] decodeBase64StringTOByte(String stringData) throws Exception {
		return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
	}

	/**
	 * This method is used to decrypt base64 encoded string using an AES 256 bit
	 * key.
	 *
	 * @param plainText : plain text to decrypt
	 * @param secret    : key to decrypt
	 * @return : Decrypted String
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(String plainText, byte[] secret)
			throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, Exception {
		final Cipher DECRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
		final SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
		DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
		return DECRYPT_CIPHER.doFinal(decodeBase64StringTOByte(plainText));
	}

	/**
	 * This method is used to encode bytes[] to base64 string.
	 *
	 * @param bytes : Bytes to encode
	 * @return : Encoded Base64 String
	 */
	public static String encodeBase64String(byte[] bytes) {
		return new String(java.util.Base64.getEncoder().encode(bytes));
	}

	/**
	 * This method is used to encrypt base64 encoded string using an AES 256 bit
	 * key.
	 *
	 * @param plainText : plain text to decrypt
	 * @param secret    : key to encrypt
	 * @return : Encrypted String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encrypt(String plainText, byte[] secret) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Cipher ENCRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
		final SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
		ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
		return Base64.encodeBase64String(ENCRYPT_CIPHER.doFinal(plainText.getBytes()));

	}

	/**
	 * This method is used to create generate HMAC
	 *
	 * @param data
	 * @param ek
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalStateException
	 * @throws UnsupportedEncodingException
	 */
	public static String generateHmac(String data, byte[] ek)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
		final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		final SecretKeySpec secret_key = new SecretKeySpec(ek, "AES_ALGORITHM");
		sha256_HMAC.init(secret_key);
		final String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes(CHARACTER_ENCODING)));
		return hash;
	}

	public static GstUtil getGstUtilInstance() {
		return obj;
	}

	public static void main(String args[]) {

	}

	/**
	 * This method is used to uncompressed and read the content from URL
	 *
	 * @param fileurl
	 * @return
	 * @throws IOException
	 */
	public static List<String> uncompressTarGZ(URL fileurl) throws IOException {

		final List<String> strlist = new ArrayList<>();
		InputStreamReader isr = null;
		try (InputStream is = fileurl.openStream()) {
			try (BufferedInputStream bis = new BufferedInputStream(is)) {
				try (GzipCompressorInputStream gcis = new GzipCompressorInputStream(bis)) {
					try (TarArchiveInputStream tarIn = new TarArchiveInputStream(gcis)) {
						TarArchiveEntry tarEntry = tarIn.getNextTarEntry();
						while (Objects.nonNull(tarEntry)) {
							if (!tarEntry.isDirectory()) {
								isr = new InputStreamReader(tarIn, Charsets.UTF_8);
								strlist.add(CharStreams.toString(isr));
							}
							tarEntry = tarIn.getNextTarEntry();
						}

					}
				}

			}
		}
		isr.close();
		return strlist;
	}

	/**
	 * This method is used to encrypt the string , passed to it using a public key
	 * provided
	 *
	 * @param planTextToEncrypt : Text to encrypt
	 * @return :encrypted string
	 * @throws CertificateException
	 * @throws IOException
	 */
	public String encryptwithPK_PEM(byte[] planTextToEncrypt)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Path keyPath = Paths.get(System.getProperty("user.dir") + "//" + PUBLIC_KEY_PATH_PEM);

		final String keyContent = new String(Files.readAllBytes(keyPath)).replace("-----BEGIN RSA PUBLIC KEY-----", "");
		final String modifiedKeyContent = keyContent.replace("-----END RSA PUBLIC KEY-----", "");
		// System.out.println(System.getProperty("user.dir") + "//" +
		// PUBLIC_KEY_PATH_PEM);
		final byte[] decodedKey = Base64.decodeBase64(modifiedKeyContent.getBytes());
		final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedKey);
		final KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		final PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		final Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		final byte[] encryptedByte = cipher.doFinal(planTextToEncrypt);
		return new String(java.util.Base64.getEncoder().encode(encryptedByte));

	}

	/**
	 * This method is used to generate secure key
	 *
	 * @return
	 * @throws Exception
	 */
	public String generateSecureKey() throws Exception {
		KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
		KEYGEN.init(ENC_BITS);
		return encodeBase64String(KEYGEN.generateKey().getEncoded());
	}

}
