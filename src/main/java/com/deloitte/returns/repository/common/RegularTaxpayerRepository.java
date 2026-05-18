package com.deloitte.returns.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.RegularTaxpayer;

//============================================================
//REPOSITORY
//============================================================

@Repository
public interface RegularTaxpayerRepository extends JpaRepository<RegularTaxpayer, Long> {

	@Query(value = """
			SELECT DISTINCT ON (gstin)
			       *
			FROM gst_api_registration."RegularTaxpayer"
			WHERE authstatus = 'A'
			ORDER BY gstin, apprvdt DESC
			""", nativeQuery = true)
	List<RegularTaxpayer> findLatestActiveGstin();
}
