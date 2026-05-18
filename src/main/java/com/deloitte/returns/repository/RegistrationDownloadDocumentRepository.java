package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DownloadDocument.RegistrationDownloadDocument;

@Repository
public interface RegistrationDownloadDocumentRepository extends JpaRepository<RegistrationDownloadDocument, Long> {

}
