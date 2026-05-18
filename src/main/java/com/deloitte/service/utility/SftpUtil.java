package com.deloitte.service.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class SftpUtil {

	private static final String HOST = "10.79.1.151";
	private static final String folderNamePaymentVa = "Payment_Deloitte";
	private static final int PORT = 22;

	// choose required user
	private static final String USERNAME = "cot";
	private static final String PASSWORD = "Passw0rd@123#";

	private static final String BASE_PATH = "/var/gst_files/GST_FILES/Return_Auto/";

	public static String uploadFile(File file, String folderName, String date) {

		Session session = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();

			session = jsch.getSession(USERNAME, HOST, PORT);
			session.setPassword(PASSWORD);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect(10000);

			Channel channel = session.openChannel("sftp");
			channel.connect();

			channelSftp = (ChannelSftp) channel;
			String folderDate = date.replace("-", "_");
			// String remoteDir = BASE_PATH + folderName;
			String remoteDir = BASE_PATH + folderName + "/" + folderDate;
			System.out.println("Remote folder: " + folderName);
			System.out.println("Remote path: " + remoteDir);

			// create folder
			createDirectoryIfNotExists(channelSftp, remoteDir);

			// go inside
			channelSftp.cd(remoteDir);

			// generate sequence
			int sequence = getNextFileSequence(channelSftp, remoteDir, date);

			// new filename
			String newFileName = date + "_" + sequence + "_downloaded_file.gz";

			// Upload file
			// upload
			try (InputStream inputStream = new FileInputStream(file)) {
				channelSftp.put(inputStream, newFileName);
			}

			String remoteFilePath = remoteDir + "/" + newFileName;

			System.out.println("File uploaded successfully to: " + remoteFilePath);

			return remoteFilePath;

		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException("SFTP upload failed for file: " + file.getName(), e);

		} finally {
			if (channelSftp != null) {
				channelSftp.exit();
			}

			if (session != null) {
				session.disconnect();
			}
		}
	}

	private static void createDirectoryIfNotExists(ChannelSftp channelSftp, String path) {

		try {
			channelSftp.cd(path);
			System.out.println("Folder already exists: " + path);

		} catch (Exception e) {

			try {
				String[] folders = path.split("/");
				String currentPath = "";

				for (String folder : folders) {

					if (folder == null || folder.trim().isEmpty()) {
						continue;
					}

					currentPath += "/" + folder;

					try {
						channelSftp.cd(currentPath);

					} catch (Exception ex) {
						System.out.println("Creating folder: " + currentPath);
						channelSftp.mkdir(currentPath);
					}
				}

			} catch (Exception ex) {
				throw new RuntimeException("Failed to create remote directory: " + path, ex);
			}
		}
	}

	private static int getNextFileSequence(ChannelSftp channelSftp, String remoteDir, String date) {
		try {
			Vector<ChannelSftp.LsEntry> files = channelSftp.ls(remoteDir);

			int max = 0;
			String prefix = date + "_"; // 01-05-2026_

			for (ChannelSftp.LsEntry entry : files) {
				String name = entry.getFilename();

				if (name.startsWith(prefix)) {
					try {
						String numPart = name.replace(prefix, "").split("_")[0];
						int num = Integer.parseInt(numPart);
						if (num > max)
							max = num;
					} catch (Exception ignored) {
					}
				}
			}

			return max + 1;

		} catch (Exception e) {
			return 1; // folder empty
		}
	}

	public static String uploadJsonFile(String jsonContent, String folderNamePayment, String date, String fileName) {

		Session session = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();

			session = jsch.getSession(USERNAME, HOST, PORT);
			session.setPassword(PASSWORD);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect(10000);

			Channel channel = session.openChannel("sftp");
			channel.connect();

			channelSftp = (ChannelSftp) channel;

			String folderDate = date.replace("-", "_");

			String remoteDir = BASE_PATH + folderNamePaymentVa + "/" + folderDate;

			// create folder if not exists
			createDirectoryIfNotExists(channelSftp, remoteDir);

			channelSftp.cd(remoteDir);

			// Upload JSON as stream
			InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));

			channelSftp.put(inputStream, fileName);

			String remoteFilePath = remoteDir + "/" + fileName;

			System.out.println("JSON uploaded to: " + remoteFilePath);

			return remoteFilePath;

		} catch (Exception e) {
			throw new RuntimeException("SFTP JSON upload failed", e);
		} finally {
			if (channelSftp != null)
				channelSftp.exit();
			if (session != null)
				session.disconnect();
		}
	}

	public static String uploadFileToSftp(byte[] fileBytes, String remoteDir, String fileName) {

		Session session = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();

			session = jsch.getSession(USERNAME, HOST, PORT);
			session.setPassword(PASSWORD);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect(10000);

			Channel channel = session.openChannel("sftp");
			channel.connect();

			channelSftp = (ChannelSftp) channel;

			// 👉 Create directory if not exists
			createDirectoryIfNotExists(channelSftp, remoteDir);

			channelSftp.cd(remoteDir);

			// 👉 Upload using byte[]
			InputStream inputStream = new ByteArrayInputStream(fileBytes);

			channelSftp.put(inputStream, fileName);

			String remoteFilePath = remoteDir + "/" + fileName;

			System.out.println("File uploaded to SFTP: " + remoteFilePath);

			return remoteFilePath;

		} catch (Exception e) {
			throw new RuntimeException("SFTP upload failed", e);
		} finally {
			if (channelSftp != null)
				channelSftp.exit();
			if (session != null)
				session.disconnect();
		}
	}

}
