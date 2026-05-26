package com.deloitte.returns.repository.registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.RegularTaxpayer;
import com.deloitte.service.utility.procedure.LedgerGstinProjection;

@Repository
public interface RegularTaxpayerRepository extends JpaRepository<RegularTaxpayer, Long> {

	@Query(value = """
			SELECT *
			FROM gst_api_ledger.get_gstin_for_ledger(
			    :frdt,
			    :todt,
			    :ledgertyp
			)
			""", nativeQuery = true)
	List<LedgerGstinProjection> getGstinForLedgerProcedure(@Param("frdt") String frdt, @Param("todt") String todt,
			@Param("ledgertyp") String ledgertyp);

	// ============================================================
	// REPOSITORY
	// ============================================================

	@Query(value = """
			SELECT DISTINCT ON (gstin)
			       *
			FROM gst_api_registration."RegularTaxpayer"
			WHERE authstatus = 'A'
			ORDER BY gstin, apprvdt DESC
			""", nativeQuery = true)
	List<RegularTaxpayer> findLatestActiveGstin();
}