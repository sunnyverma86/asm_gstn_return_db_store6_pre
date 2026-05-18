package com.deloitte.service.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.deloitte.returns.repository.eway.EWayBillBeanRepository;
import com.deloitte.returns.repository.eway.EwayFileCountResponseRepository;
import com.deloitte.returns.repository.eway.EwayFileDetailResponseRepository;
import com.deloitte.returns.repository.eway.EwayGzJsonStorageRepository;
import com.deloitte.returns.repository.eway.EwbCountDataRepository;
import com.deloitte.returns.repository.eway.EwbDetailsDataRepository;
import com.deloitte.returns.repositoryCommon.DateEwayGzFilePathRepository;

import tools.jackson.databind.ObjectMapper;

public class AbstractSupportEwayBillApiService {

	@Autowired
	protected EWayBillBeanRepository eWayBillBeanRepository;

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
	protected EwayFileCountResponseRepository ewayFileCountResponseRepository;

	@Autowired
	protected EwbCountDataRepository ewbCountDataRepository;

	@Autowired
	protected EwayFileDetailResponseRepository ewayFileDetailResponseRepository;

	@Autowired
	protected EwbDetailsDataRepository ewbDetailsDataRepository;

	@Autowired
	protected EwayGzJsonStorageRepository ewayGzJsonStorageRepository;

	@Autowired
	protected DateEwayGzFilePathRepository dateEwayGzFilePathRepository;

	protected static final int HARD_MAX_RETRY = 5;
	protected static final int IO_BUFFER_SIZE = 8192;

	protected static final int BATCH_SIZE = 1500; // Adjust the batch size as needed
	protected static final int THREAD_POOL_SIZE = 150; // Adjust the number of threads as needed

	protected static byte[] rekBytes;
	protected static byte[] sekBytes;
	protected static byte[] appKey = null;

	protected final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	protected RestTemplate restTemplate;

}
