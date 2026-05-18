package com.deloitte.service.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1.EnforcementOfficerGSTR2A;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1.EnforcementOfficerGSTR3B;
import com.deloitte.returns.entity.type.EnforcementOfficerGSTR71.EnforcementOfficerGSTR7;
import com.deloitte.returns.entity.type.EnforcementOfficerRecordSearchPayments1.EnforcementOfficerRecordSearchPayments;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileDownloadHelperForEnforcement {
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadHelperForEnforcement.class);

	public static boolean createFilesInFolder(String fileName, String folderName, String rawJson) {
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			boolean fileCreated = createFile(folderName, fileName, rawJson);
			if (fileCreated) {
				logger.info(fileName + " file created successfully");
				return true;
			} else {
				logger.info(fileName + " failed to create file");
				return false;
			}
		} else {
			logger.info(folderName + " failed to create folder");
			return false;
		}
	}

	public static void createFolders(String pathString) throws IOException {
		Path path = Paths.get(pathString);
		Files.createDirectories(path);
	}

	public static boolean createFile(String folderName, String fileName, String rawJson) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					FileWriter writer = new FileWriter(file);
					writer.write(rawJson); // ⭐ write raw JSON directly
					writer.close();
				}
				return created;
			} else {
				logger.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

//	public static boolean createFilesInFolder(String fileName, String folderName,
//			EnforcementOfficerGSTR1 enforcementOfficerGSTR1) {
//
//		// Creating a new folder
//		try {
//			createFolders(folderName);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		boolean folderCreated = new File(folderName).exists();
//		if (folderCreated) {
//			// Creating a new file inside the folder
//			boolean fileCreated = createFile(folderName, fileName, enforcementOfficerGSTR1);
//			if (fileCreated) {
//				logger.info(fileName + " file created successfully");
//				return true;
//			} else {
//				logger.info(fileName + " failed to create file");
//				return false;
//			}
//		} else {
//			logger.info(folderName + "Failed to create folder");
//			return false;
//		}
//	}
//
//	public static void createFolders(String pathString) throws IOException {
//		Path path = Paths.get(pathString);
//		// Create directories if they do not exist
//		Files.createDirectories(path);
//	}
//
//
//
////	// Method to create a new file inside a folder
//	public static boolean createFile(String folderName, String fileName,
//			EnforcementOfficerGSTR1 enforcementOfficerGSTR1) {
//		File folder = new File(folderName);
//		File file = new File(folder, fileName);
//
//		try {
//			// If the file does not exist, create it
//			if (!file.exists()) {
//				boolean created = file.createNewFile();
//				if (created) {
//					// Write some content to the file
//					FileWriter writer = new FileWriter(file);
//					writer.write(new ObjectMapper().writeValueAsString(enforcementOfficerGSTR1));
//					writer.close();
//				}
//				return created;
//			} else {
//				logger.info(fileName + " file exists");
//				return false;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return false; // File already exists or failed to create
//	}
//	

	public static boolean createFilesInFolderForGstr3b(String fileName, String folderName,
			EnforcementOfficerGSTR3B enforcementOfficerGSTR3B) {

		// Creating a new folder
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			// Creating a new file inside the folder
			boolean fileCreated = createFileForGstr3b(folderName, fileName, enforcementOfficerGSTR3B);
			if (fileCreated) {
				logger.info(fileName + " file created successfully");
				return true;
			} else {
				logger.info(fileName + " failed to create file");
				return false;
			}
		} else {
			logger.info(folderName + "Failed to create folder");
			return false;
		}
	}

	public static boolean createFileForGstr3b(String folderName, String fileName,
			EnforcementOfficerGSTR3B enforcementOfficerGSTR3B) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					// Write some content to the file
					FileWriter writer = new FileWriter(file);
					writer.write(new ObjectMapper().writeValueAsString(enforcementOfficerGSTR3B));
					writer.close();
				}
				return created;
			} else {
				logger.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // File already exists or failed to create
	}

	// gstr7
	public static boolean createFilesInFolderForGstr7(String fileName, String folderName,
			EnforcementOfficerGSTR7 enforcementOfficerGSTR7) {

		// Creating a new folder
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			// Creating a new file inside the folder
			boolean fileCreated = createFileForGstr7(folderName, fileName, enforcementOfficerGSTR7);
			if (fileCreated) {
				logger.info(fileName + " file created successfully");
				return true;
			} else {
				logger.info(fileName + " failed to create file");
				return false;
			}
		} else {
			logger.info(folderName + "Failed to create folder");
			return false;
		}
	}

	public static boolean createFileForGstr7(String folderName, String fileName,
			EnforcementOfficerGSTR7 enforcementOfficerGSTR7) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					// Write some content to the file
					FileWriter writer = new FileWriter(file);
					writer.write(new ObjectMapper().writeValueAsString(enforcementOfficerGSTR7));
					writer.close();
				}
				return created;
			} else {
				logger.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // File already exists or failed to create
	}

	// gstr2a
	public static boolean createFilesInFolderForGstr2A(String fileName, String folderName,
			EnforcementOfficerGSTR2A enforcementOfficerGSTR2A) {

		// Creating a new folder
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			// Creating a new file inside the folder
			boolean fileCreated = createFileForGstr2A(folderName, fileName, enforcementOfficerGSTR2A);
			if (fileCreated) {
				logger.info(fileName + " file created successfully");
				return true;
			} else {
				logger.info(fileName + " failed to create file");
				return false;
			}
		} else {
			logger.info(folderName + "Failed to create folder");
			return false;
		}
	}

	public static boolean createFileForGstr2A(String folderName, String fileName,
			EnforcementOfficerGSTR2A enforcementOfficerGSTR2A) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					// Write some content to the file
					FileWriter writer = new FileWriter(file);
					writer.write(new ObjectMapper().writeValueAsString(enforcementOfficerGSTR2A));
					writer.close();
				}
				return created;
			} else {
				logger.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // File already exists or failed to create
	}

	//////////////////////////////// RSP/////////////
	public static boolean createFilesInFolderForRSP(String fileName, String folderName,
			EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPayments) {

		// Creating a new folder
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			// Creating a new file inside the folder
			boolean fileCreated = createFileForRSP(folderName, fileName, enforcementOfficerRecordSearchPayments);
			if (fileCreated) {
				logger.info(fileName + " file created successfully");
				return true;
			} else {
				logger.info(fileName + " failed to create file");
				return false;
			}
		} else {
			logger.info(folderName + "Failed to create folder");
			return false;
		}
	}

	public static boolean createFileForRSP(String folderName, String fileName,
			EnforcementOfficerRecordSearchPayments enforcementOfficerRecordSearchPayments) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					// Write some content to the file
					FileWriter writer = new FileWriter(file);
					writer.write(new ObjectMapper().writeValueAsString(enforcementOfficerRecordSearchPayments));
					writer.close();
				}
				return created;
			} else {
				logger.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // File already exists or failed to create
	}

}
