package com.deloitte.service.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.deloitte.returns.entity.Gstr2a.Gstr2a;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class FileDownloadHelperForGstr2a {

	public boolean createFilesInFolder(String fileName, String folderName, Gstr2a gstr2a) {

		// Creating a new folder
		try {
			createFolders(folderName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		boolean folderCreated = new File(folderName).exists();
		if (folderCreated) {
			// Creating a new file inside the folder
			boolean fileCreated = createFile(folderName, fileName, gstr2a);
			if (fileCreated) {
				log.info(fileName + " file created successfully");
				return true;
			} else {
				log.info(fileName + " failed to create file");
				return false;
			}
		} else {
			log.info(folderName + "Failed to create folder");
			return false;
		}
	}

	public void createFolders(String pathString) throws IOException {
		Path path = Paths.get(pathString);
		// Create directories if they do not exist
		Files.createDirectories(path);
	}

	// Method to create a new file inside a folder
	public boolean createFile(String folderName, String fileName, Gstr2a gstr2a) {
		File folder = new File(folderName);
		File file = new File(folder, fileName);

		try {
			// If the file does not exist, create it
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (created) {
					// Write some content to the file
					FileWriter writer = new FileWriter(file);
					writer.write(new ObjectMapper().writeValueAsString(gstr2a));
					writer.close();
				}
				return created;
			} else {
				log.info(fileName + " file exists");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; // File already exists or failed to create
	}

}
