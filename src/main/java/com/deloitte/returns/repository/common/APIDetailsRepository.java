package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.common.entity.APIDetails;

public interface APIDetailsRepository extends JpaRepository<APIDetails, Long> {

}
