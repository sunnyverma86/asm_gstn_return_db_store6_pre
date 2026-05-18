package com.deloitte.service.abs;

import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.returns.repository.Gstr2aRepository;
import com.deloitte.returns.repository.ReconRepository;
import com.deloitte.returns.repository.common.FileDetailsRepository;
import com.deloitte.returns.repository.common.FileNameGstr2aRepository;
import com.deloitte.returns.repository.common.GstinRepository;
import com.deloitte.returns.repository.common.ReturnDetailResponseRepository;
import com.deloitte.returns.repository.common.ReturnFileCountResponseRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR1Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR1SumRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR2ARepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR3BRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR4Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR5Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR6Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR7Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR8Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerGSTR9Repository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchPaymentsRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchRegistrationRepository;
import com.deloitte.returns.repository.enforcement.EnforcementOfficerRecordSearchReturnsRepository;
import com.deloitte.returns.repository.enforcement.FileDetaisServiceEnforcementImpl;
import com.deloitte.returns.repository.enforcement.FileNameEnforcementRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSERepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSPRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSRRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforcementRSRegisRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr1Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr1SumRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr2ARepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr3bRepository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr4Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr5Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr6Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr7Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr8Repository;
import com.deloitte.returns.repository.enforcement.GstinEnforecementGstr9Repository;
import com.deloitte.returns.repositoryCommon.DateCountDataRepository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.service.support.CommonServiceGstrImplSupport;
import com.deloitte.service.support.FileDownloadHelperCommon;
import com.deloitte.service.support.FileDownloadHelperForGstr2a;
import com.deloitte.service.support.LogbackConfig;

public class AbstractServiceClassForAll {

	@Autowired
	protected MasterDataService masterDataService;

	@Autowired
	protected GstUserSessionServices gstUserSessionServices;

	@Autowired
	protected RestClientHelper restClient;

	@Autowired
	protected CommonServiceGstrImplSupport commonServiceGstrImplSupport;

	@Autowired
	protected AuthenticationHelper authenticationHelper;

	@Autowired
	protected ReturnFileCountResponseRepository returnFileCountRepository;

	@Autowired
	protected ReturnDetailResponseRepository returnDetailResponseRepository;

	@Autowired
	protected DateCountDataRepository dateCountDataRepository;

	@Autowired
	protected FileDownloadHelperCommon fileDownloadHelperCommon;

	@Autowired
	protected APIDetailsImpl apiDetailsImpl;

	@Autowired
	protected ReconRepository reconRepository;

	@Autowired
	protected GstinRepository gstinRepository;

	@Autowired
	protected LogbackConfig logbackConfig;

	@Autowired
	protected FileDownloadHelperForGstr2a fileDownloadHelperForGstr2a;

	@Autowired
	protected FileDetailsRepository fileDetailsRepository;

	@Autowired
	protected FileNameGstr2aRepository fileNameGstr2aRepository;

	@Autowired
	protected Gstr2aRepository gstr2aRepository;

	@Autowired
	protected GstinEnforecementGstr1Repository gstinEnforecementGstr1Repository;

	@Autowired
	protected GstinEnforecementGstr3bRepository gstinEnforecementGstr3bRepository;

	@Autowired
	protected GstinEnforecementGstr7Repository gstinEnforecementGstr7Repository;

	@Autowired
	protected GstinEnforecementGstr2ARepository gstinEnforecementGstr2ARepository;

	@Autowired
	protected GstinEnforecementGstr4Repository gstinEnforecementGstr4Repository;

	@Autowired
	protected GstinEnforecementGstr5Repository gstinEnforecementGstr5Repository;

	@Autowired
	protected GstinEnforecementGstr6Repository gstinEnforecementGstr6Repository;

	@Autowired
	protected GstinEnforecementGstr1SumRepository gstinEnforecementGstr1SumRepository;

	@Autowired
	protected GstinEnforecementGstr8Repository gstinEnforecementGstr8Repository;

	@Autowired
	protected GstinEnforecementGstr9Repository gstinEnforecementGstr9Repository;

	@Autowired
	protected GstinEnforcementRSERepository gstinEnforcementRSERepository;

	@Autowired
	protected GstinEnforcementRSPRepository gstinEnforcementRSPRepository;

	@Autowired
	protected GstinEnforcementRSRegisRepository gstinEnforcementRSRegisRepository;

	@Autowired
	protected GstinEnforcementRSRRepository gstinEnforcementRSRRepository;

	@Autowired
	protected FileDetaisServiceEnforcementImpl fileDetaisServiceImpl;

	@Autowired
	protected FileNameEnforcementRepository fileNameEnforcementRepository;

	@Autowired
	protected EnforcementOfficerGSTR1Repository enforcementOfficerGSTR1Repository;

	@Autowired
	protected EnforcementOfficerGSTR3BRepository enforcementOfficerGSTR3BRepository;

	@Autowired
	protected EnforcementOfficerGSTR2ARepository enforcementOfficerGSTR2ARepository;

	@Autowired
	protected EnforcementOfficerGSTR7Repository enforcementOfficerGSTR7Repository;

	@Autowired
	protected EnforcementOfficerGSTR4Repository enforcementOfficerGSTR4Repository;

	@Autowired
	protected EnforcementOfficerGSTR5Repository enforcementOfficerGSTR5Repository;

	@Autowired
	protected EnforcementOfficerGSTR6Repository enforcementOfficerGSTR6Repository;

	@Autowired
	protected EnforcementOfficerGSTR1SumRepository enforcementOfficerGSTR1SumRepository;

	@Autowired
	protected EnforcementOfficerGSTR8Repository enforcementOfficerGSTR8Repository;

	@Autowired
	protected EnforcementOfficerGSTR9Repository enforcementOfficerGSTR9Repository;

	@Autowired
	protected EnforcementOfficerRecordSearchRegistrationRepository enforcementOfficerRecordSearchRegistrationRepository;

	@Autowired
	protected EnforcementOfficerRecordSearchPaymentsRepository enforcementOfficerRecordSearchPaymentsRepository;

	@Autowired
	protected EnforcementOfficerRecordSearchReturnsRepository enforcementOfficerRecordSearchReturnsRepository;
	
	protected static final int BATCH_SIZE = 150; // Adjust the batch size as needed
	protected static final int THREAD_POOL_SIZE = 15; // Adjust the number of threads as needed

}
