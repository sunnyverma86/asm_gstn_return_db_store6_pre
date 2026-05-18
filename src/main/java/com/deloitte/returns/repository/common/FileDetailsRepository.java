package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr2a.FileDetails;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Long> {
}
