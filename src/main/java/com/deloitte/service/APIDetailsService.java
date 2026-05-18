package com.deloitte.service;

import java.util.List;

import com.deloitte.common.entity.APIDetails;

public interface APIDetailsService {

	APIDetails create(APIDetails apiDetails);

	APIDetails update(APIDetails apiDetails);

	APIDetails findByID(Long Id);

	List<APIDetails> findAPIDetailsBeanAll();

	APIDetails findByName(String name);

	void deleteByID(Long Id);

}
