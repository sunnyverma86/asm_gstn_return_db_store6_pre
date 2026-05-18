package com.deloitte.returns.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr2a.FileNameGstr2a;

@Repository
public interface FileNameGstr2aRepository extends JpaRepository<FileNameGstr2a, Long> {

	Optional<FileNameGstr2a> findByFileName(String FileNameGstr2aStr);

	List<FileNameGstr2a> findAllByIsProcessedFalseOrderById();
}
