package com.deloitte.returns.repository.eway;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.EwayBill.EwayBill_Ewb;

@Repository
public interface EwayBillEwbRepository extends JpaRepository<EwayBill_Ewb, Long> {

	Optional<EwayBill_Ewb> findByEwbNoOrderByIdDesc(long ewbNo);

	Optional<EwayBill_Ewb> findFirstByEwbNoOrderByIdDesc(long ewbNo);

	@Query(value = """
			SELECT DISTINCT ON (ewb_nos) *
			FROM eway_live_eway_bill_new.ewb
			WHERE doc_dt = :docDate
			ORDER BY ewb_nos, id
			""", countQuery = """
			SELECT COUNT(*)
			FROM (
			    SELECT DISTINCT ewb_nos
			    FROM eway_live_eway_bill_new.ewb
			    WHERE doc_dt = :docDate
			) t
			""", nativeQuery = true)
	Page<EwayBill_Ewb> findByDocDate(@Param("docDate") String docDate, Pageable pageable);

	@Query(value = """
			SELECT DISTINCT ON (ewb_nos) *
			FROM eway_live_eway_bill_new.ewb
			WHERE doc_dt = :docDate
			  AND status = :status
			ORDER BY ewb_nos, id
			""", countQuery = """
			SELECT COUNT(*)
			FROM (
			    SELECT DISTINCT ewb_nos
			    FROM eway_live_eway_bill_new.ewb
			    WHERE doc_dt = :docDate
			      AND status = :status
			) t
			""", nativeQuery = true)
	Page<EwayBill_Ewb> findByDocDateAndStatus(@Param("docDate") String docDate, @Param("status") String status,
			Pageable pageable);

	List<EwayBill_Ewb> findByDocDt(String docDate);

	List<EwayBill_Ewb> findByDocDtAndStatus(String docDate, String status);

}
