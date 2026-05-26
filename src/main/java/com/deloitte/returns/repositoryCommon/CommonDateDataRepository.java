package com.deloitte.returns.repositoryCommon;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.filecounter.CommonDateData;

public interface CommonDateDataRepository extends JpaRepository<CommonDateData, Long> {

	CommonDateData findByDateAndApplication(String date, String application);

}
