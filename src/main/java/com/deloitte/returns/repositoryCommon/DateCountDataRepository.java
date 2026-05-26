package com.deloitte.returns.repositoryCommon;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.DateCountData;

@Repository
public interface DateCountDataRepository extends JpaRepository<DateCountData, Long> {

	List<DateCountData> findByDateAndApplicationAndIsProcessed(String date, String application, String string);

	Optional<DateCountData> findByDateAndApplicationAndCountAndIsProcessed(String date, String application,
			String fileNum, String string);

	Optional<DateCountData> findByDateAndApplicationAndCount(String date, String application, String fileNum);
	
	
	boolean existsByDateAndApplicationAndIsProcessed(
	        String date,
	        String application,
	        String isProcessed);

	List<DateCountData> findByDateAndApplication(String date, String application);

	Optional<DateCountData> findTopByDateAndApplicationAndCountOrderByUpdatedDateTimeDesc(String date,
			String application, String fileNum);

}
