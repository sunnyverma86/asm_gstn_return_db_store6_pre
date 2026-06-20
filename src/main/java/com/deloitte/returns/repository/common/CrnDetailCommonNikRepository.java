package com.deloitte.returns.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.CrnDetailCommonNik;

@Repository
public interface CrnDetailCommonNikRepository extends JpaRepository<CrnDetailCommonNik, Long> {

	@Query("""
			    SELECT c
			    FROM CrnDetailCommonNik c
			    WHERE c.fy IS NULL
			""")
	Page<CrnDetailCommonNik> findPending(Pageable pageable);

}
