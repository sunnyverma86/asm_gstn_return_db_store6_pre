package com.deloitte.service.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.deloitte.returns.repository.eway.DateInfoEwayBillRepository;
import com.deloitte.service.support.SupportEwayBillApiService;

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

}
