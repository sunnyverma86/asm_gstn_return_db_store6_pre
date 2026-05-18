package com.deloitte.returns.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.regis.RegistrationDataJsonFileView;

@Repository
public interface RegistrationDataJsonFileViewRepository extends JpaRepository<RegistrationDataJsonFileView, Long> {

	List<RegistrationDataJsonFileView> findAllByIdentityTypeAndIsProcessedFalseOrderById(String identityType);

	List<RegistrationDataJsonFileView> findAllByIdentityTypeAndIsProcessedFalseAndDownloadFileStatusTrueOrderById(
			String identityType);

}
