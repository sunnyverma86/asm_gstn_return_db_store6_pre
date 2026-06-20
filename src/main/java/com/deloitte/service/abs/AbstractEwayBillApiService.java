package com.deloitte.service.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.deloitte.returns.repository.eway.DateInfoEwayBillRepository;
import com.deloitte.returns.repository.eway.EWayBillBeanRepository;
import com.deloitte.returns.repository.eway.EwbCountDataRepository;
import com.deloitte.returns.repository.eway.EwbDetailsDataRepository;
import com.deloitte.service.impl.EwayBillApiSupportService;
import com.deloitte.service.support.SupportEwayBillApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractEwayBillApiService {

	@Value("${clientId}")
	protected String clientId;

	@Value("${clientSecret}")
	protected String clientSecret;

	@Value("${stateCode}")
	protected String stateCode;

	@Value("${ewayBill.file.gz.file}")
	protected String ewayBillFileGzFile;

	@Value("${ewayBill.file.gz.file.extracted}")
	protected String ewayBillFileGzFileExtracted;

	@Value("${eway_common_url}")
	protected String ewayUrl;

	@Value("${start.date.ewaybill}")
	protected String startDateEwaybill;

	@Value("${ewayBill.file.count}")
	protected String ewayBillFileCount;

	@Autowired
	protected DateInfoEwayBillRepository dateInfoEwayBillRepository;

	@Autowired
	protected SupportEwayBillApiService supportEwayBillApiService;

	@Autowired
	protected EwbCountDataRepository ewbCountDataRepository;

	@Autowired
	protected EwbDetailsDataRepository ewbDetailsDataRepository;

	@Autowired
	protected EWayBillBeanRepository eWayBillBeanRepository;

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected EwayBillApiSupportService ewayBillApiSupportService;

}
