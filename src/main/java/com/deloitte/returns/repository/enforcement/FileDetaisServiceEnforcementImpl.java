package com.deloitte.returns.repository.enforcement;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.returns.entity.type.EnforcementOfficerGSTR1.FileNameEnforcement;

@Service
public class FileDetaisServiceEnforcementImpl {

	private static final Logger logger = LoggerFactory.getLogger(FileDetaisServiceEnforcementImpl.class);

	@Autowired
	private FileDetailEnforcementsRepository fileDetailsRepository;

	public FileNameEnforcement create(FileNameEnforcement fileDetails) {
		return fileDetailsRepository.save(fileDetails);
	}

	public void createAll(List<FileNameEnforcement> fileDetails) {
		logger.info("Method{}:createAll");
		fileDetailsRepository.saveAllAndFlush(fileDetails);
	}

	public FileNameEnforcement update(FileNameEnforcement fileDetails) {
		return fileDetailsRepository.save(fileDetails);
	}

	public FileNameEnforcement getFileDetailsId(Long Id) {
		return fileDetailsRepository.findById(Id).get();
	}

	public FileNameEnforcement getFileDetailsName(String name) {
		return null;
	}

	public List<FileNameEnforcement> getAllFileDetails() {
		return fileDetailsRepository.findAll();
	}

	public List<FileNameEnforcement> getFileDetailsByType(String type) {
		return getAllFileDetails().parallelStream().filter(x -> x.getType().equalsIgnoreCase(type))
				.collect(Collectors.toList());
	}
}
