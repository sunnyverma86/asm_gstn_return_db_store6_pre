package com.deloitte.returns.repositoryCommon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DownloadDocument.FileNameDocument;

@Repository
public interface FileNameDocumentRepository extends JpaRepository<FileNameDocument, Long> {
	List<FileNameDocument> findAllByIsProcessedFalseOrderById();

	boolean existsByGstinAndFileName(String gstin, String fileName);

}
