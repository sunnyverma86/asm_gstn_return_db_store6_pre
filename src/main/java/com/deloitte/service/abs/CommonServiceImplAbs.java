package com.deloitte.service.abs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.deloitte.returns.repository.NormalRegistrationRepository;
import com.deloitte.returns.repository.ReconRepository;
import com.deloitte.returns.repository.RegDocumentsRepository;
import com.deloitte.returns.repository.RegisDcupdtlsTestingRepository;
import com.deloitte.returns.repository.RentActivePpbzdtlsRepository;
import com.deloitte.returns.repository.TdsTcsRegistrationRepository;
import com.deloitte.returns.repository.common.AlertJsonRepository;
import com.deloitte.returns.repository.common.GstinRepository;
import com.deloitte.returns.repository.common.LedgerDataJsonFileRepository;
import com.deloitte.returns.repository.common.RegistrationDataJsonFileRepository;
import com.deloitte.returns.repository.common.RegistrationDataJsonFileViewRepository;
import com.deloitte.returns.repository.common.ReturnDetailResponseRepository;
import com.deloitte.returns.repository.common.ReturnFileCountResponseRepository;
import com.deloitte.returns.repository.ledger.LedgerCashRepository;
import com.deloitte.returns.repository.ledger.LedgerItcRepository;
import com.deloitte.returns.repository.ledger.LedgerLiabilityRepository;
import com.deloitte.returns.repository.ledger.LedgerOtherRepository;
import com.deloitte.returns.repositoryCommon.AlertDetailsRegistrationRepository;
import com.deloitte.returns.repositoryCommon.AlertRegistrationRepository;
import com.deloitte.returns.repositoryCommon.DateCountDataRepository;
import com.deloitte.returns.service.AuthenticationHelper;
import com.deloitte.returns.service.GstUserSessionServices;
import com.deloitte.returns.service.MasterDataService;
import com.deloitte.service.helper.REST.call.RestClientHelper;
import com.deloitte.service.impl.APIDetailsImpl;
import com.deloitte.service.impl.ArnHandlerForRegistration;
import com.deloitte.service.impl.RegistrationServiceImpl;
import com.deloitte.service.support.ArnUpdateHandler;
import com.deloitte.service.support.CommonServiceGstrImplSupport;
import com.deloitte.service.support.FileDownloadHelperCommon;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonServiceImplAbs {

//	@Autowired
//	protected CommonAlertDateRepository commonAlertDateRepository;

	@Autowired
	protected ArnHandlerForRegistration arnHandlerForRegistration;

	@Autowired
	protected AlertRegistrationRepository alertRegistrationRepository;

	@Autowired
	protected AlertDetailsRegistrationRepository alertDetailsRegistrationRepository;

	@Autowired
	protected MasterDataService masterDataService;

	@Autowired
	protected GstUserSessionServices gstUserSessionServices;

	@Autowired
	protected AlertJsonRepository alertJsonRepository;

	@Value("${payment.file.location}")
	protected String paymentFileLocation;

	@Autowired
	protected RentActivePpbzdtlsRepository RentActivePpbzdtlsRepository;

	@Autowired
	protected RegistrationServiceImpl registrationServiceImpl;

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
	protected LedgerItcRepository ledgerItcRepository;

	@Autowired
	protected LedgerCashRepository ledgerCashRepository;

	@Autowired
	protected LedgerLiabilityRepository ledgerLiabilityRepository;

	@Autowired
	protected LedgerOtherRepository ledgerOtherRepository;

	@Autowired
	protected GstinRepository gstinRepository;

	@Autowired
	protected RegistrationDataJsonFileRepository registrationDataJsonFileRepository;

	@Autowired
	protected RegistrationDataJsonFileViewRepository registrationDataJsonFileViewRepository;

	@Autowired
	protected NormalRegistrationRepository normalRegistrationRepository;

	@Autowired
	protected TdsTcsRegistrationRepository tdsTcsRegistrationRepository;

	@Autowired
	private LedgerDataJsonFileRepository ledgerDataJsonFileRepository;

	@Autowired
	protected ObjectMapper objectMapper;

	@Value("${normal-compostion.file.location}")
	protected String normalAndCompositionFileLocation;

	@Value("${ledger.file.location}")
	protected String LedgerFileLocation;

	protected static final int THREAD_POOL_SIZE = 10;
	protected static final int BATCH_SIZE = 500;

	@Autowired
	protected RegisDcupdtlsTestingRepository regisDcupdtlsTestingRepository;

	@Autowired
	protected RegDocumentsRepository regDocumentsRepository;

	@Autowired
	protected Map<String, ArnUpdateHandler> crnCaseHandlers;

}
