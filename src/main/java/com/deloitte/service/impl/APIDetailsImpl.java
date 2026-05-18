package com.deloitte.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.deloitte.common.entity.APIDetails;
import com.deloitte.returns.repository.common.APIDetailsRepository;
import com.deloitte.service.APIDetailsService;

@Service
public class APIDetailsImpl implements APIDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(APIDetailsImpl.class);

	@Autowired
	private CacheManager cacheManager;

	private final APIDetailsRepository apiDetailsRepository;

	public APIDetailsImpl(APIDetailsRepository apiDetailsRepository) {
		this.apiDetailsRepository = apiDetailsRepository;
	}

	@Override
	public APIDetails create(APIDetails apiDetails) {
		return apiDetailsRepository.save(apiDetails);
	}

	@Override
	public APIDetails update(APIDetails apiDetails) {
		return apiDetailsRepository.save(apiDetails);
	}

	@Override
	public APIDetails findByID(Long Id) {
		return apiDetailsRepository.getReferenceById(Id);
	}

	@Override
	public List<APIDetails> findAPIDetailsBeanAll() {
		return apiDetailsRepository.findAll();
	}

	@Override
	public APIDetails findByName(String name) {
		APIDetails apiDetails = null;
		if (apiDetailsRepository.findAll().stream().filter(x -> x.getApiName().equals(name)).findAny().isPresent()) {
			apiDetails = apiDetailsRepository.findAll().stream().filter(data -> data.getApiName().equals(name))
					.findAny().get();
		} else {

		}
		return apiDetails;
	}

	@Override
	public void deleteByID(Long Id) {
		logger.info("Method{}:deleteByID");
		apiDetailsRepository.deleteById(Id);

	}

	public void getCacheData() {
		cacheManager.getCacheNames().stream().forEach(x -> System.out.println(x));
		System.out.println(cacheManager.getCache("GSTUserSession"));
	}
}
