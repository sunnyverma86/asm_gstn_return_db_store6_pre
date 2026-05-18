package com.deloitte.service.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.deloitte.returns.repository.Cmp8Repository;
import com.deloitte.returns.repository.Gstr10Repository;
import com.deloitte.returns.repository.Gstr11Repository;
import com.deloitte.returns.repository.Gstr1ARepository;
import com.deloitte.returns.repository.Gstr1Repository;
import com.deloitte.returns.repository.Gstr2bRepository;
import com.deloitte.returns.repository.Gstr3bRepository;
import com.deloitte.returns.repository.Gstr4Repository;
import com.deloitte.returns.repository.Gstr5Repository;
import com.deloitte.returns.repository.Gstr6Repository;
import com.deloitte.returns.repository.Gstr7Repository;
import com.deloitte.returns.repository.Gstr8Repository;
import com.deloitte.returns.repository.Gstr98aRepository;
import com.deloitte.returns.repository.Gstr9Repository;
import com.deloitte.returns.repository.Gstr9aRepository;
import com.deloitte.returns.repository.Gstr9cRepository;
import com.deloitte.returns.repository.Itc2Repository;
import com.deloitte.returns.repository.PaymentRepository;
import com.deloitte.returns.repository.common.Cmp08InitialJsonRepository;
import com.deloitte.returns.repository.common.Itc02InitialJsonRepository;
import com.deloitte.returns.repository.common.PaymentInitialJsonRepository;
import com.deloitte.returns.repository.common.R10InitialJsonRepository;
import com.deloitte.returns.repository.common.R11InitialJsonRepository;
import com.deloitte.returns.repository.common.R1InitialJsonRepository;
import com.deloitte.returns.repository.common.R1aInitialJsonRepository;
import com.deloitte.returns.repository.common.R2bInitialJsonRepository;
import com.deloitte.returns.repository.common.R3bInitialJsonRepository;
import com.deloitte.returns.repository.common.R4InitialJsonRepository;
import com.deloitte.returns.repository.common.R5InitialJsonRepository;
import com.deloitte.returns.repository.common.R6InitialJsonRepository;
import com.deloitte.returns.repository.common.R7InitialJsonRepository;
import com.deloitte.returns.repository.common.R8InitialJsonRepository;
import com.deloitte.returns.repository.common.R98aInitialJsonRepository;
import com.deloitte.returns.repository.common.R9InitialJsonRepository;
import com.deloitte.returns.repository.common.R9aInitialJsonRepository;
import com.deloitte.returns.repository.common.R9cInitialJsonRepository;
import com.deloitte.returns.repository.common.ReturnGzJsonStorageRepository;
import com.deloitte.returns.repositoryCommon.DateReturnGzFilePathRepository;
import com.deloitte.service.support.CommonServiceGstrImplSupport;

public abstract class AbstractFileDownloadHelperCommon {

//	@Autowired
//	protected CommonFileDetailsRepository commonFileDetailsRepository;
//
//	@Autowired
//	protected FileNameRegisNormalRepository fileNameRegisNormalRepository;

//	@Autowired
//	protected RegistrationNormalTaxPayerRepository registrationNormalTaxPayerRepository;

	@Autowired
	protected Gstr7Repository gstr7Repository;

	@Autowired
	protected Gstr1Repository gstr1Repository;

	@Autowired
	protected Gstr3bRepository gstr3bRepository;

	@Autowired
	protected Cmp8Repository cmp8Repository;

	@Autowired
	protected Gstr10Repository gstr10Repository;

	@Autowired
	protected Gstr1ARepository gstr1ARepository;

	@Autowired
	protected Gstr98aRepository gstr98aRepository;

	@Autowired
	protected Gstr6Repository gstr6Repository;

	@Autowired
	protected Gstr9cRepository gstr9cRepository;

	@Autowired
	protected Gstr9Repository gstr9Repository;

	@Autowired
	protected Gstr8Repository gstr8Repository;

	@Autowired
	protected Gstr4Repository gstr4Repository;

	@Autowired
	protected Gstr5Repository gstr5Repository;

	@Autowired
	protected Gstr11Repository gstr11Repository;

	@Autowired
	protected Gstr9aRepository gstr9aRepository;

	@Autowired
	protected Gstr2bRepository gstr2bRepository;

	@Autowired
	protected Itc2Repository itc2Repository;

	@Autowired
	protected R1InitialJsonRepository r1Repo;

	@Autowired
	protected R1aInitialJsonRepository r1aRepo;

	@Autowired
	protected R2bInitialJsonRepository r2bRepo;

	@Autowired
	protected R3bInitialJsonRepository r3bRepo;

	@Autowired
	protected R4InitialJsonRepository r4Repo;

	@Autowired
	protected R5InitialJsonRepository r5Repo;

	@Autowired
	protected R6InitialJsonRepository r6Repo;

	@Autowired
	protected R7InitialJsonRepository r7Repo;

	@Autowired
	protected R8InitialJsonRepository r8Repo;

	@Autowired
	protected R9InitialJsonRepository r9Repo;

	@Autowired
	protected R98aInitialJsonRepository r98aRepo;

	@Autowired
	protected R9aInitialJsonRepository r9aRepo;

	@Autowired
	protected R9cInitialJsonRepository r9cRepo;

	@Autowired
	protected R10InitialJsonRepository r10Repo;

	@Autowired
	protected R11InitialJsonRepository r11Repo;

	@Autowired
	protected Cmp08InitialJsonRepository cmp08Repo;

	@Autowired
	protected Itc02InitialJsonRepository itc02Repo;

	@Autowired
	protected PaymentInitialJsonRepository paymentRepo;

	@Autowired
	protected PaymentRepository paymentRepository;

	@Autowired
	protected ReturnGzJsonStorageRepository returnGzJsonStorageRepository;

	@Autowired
	protected DateReturnGzFilePathRepository dateReturnGzFilePathRepository;

	@Autowired
	protected CommonServiceGstrImplSupport commonServiceGstrImplSupport;

	@Value("${cmp8.file.location}")
	protected String cmp8FileLocation;

	@Value("${gstr10.file.location}")
	protected String gstr10FileLocation;

	@Value("${gstr1A.file.location}")
	protected String gstr1AFileLocation;

	@Value("${gstr1.file.location}")
	protected String gstr1FileLocation;

	@Value("${gstr3b.file.location}")
	protected String gstr3bFileLocation;

	@Value("${gstr4.file.location}")
	protected String gstr4FileLocation;

	@Value("${gstr5.file.location}")
	protected String gstr5FileLocation;

	@Value("${gstr6.file.location}")
	protected String gstr6FileLocation;

	@Value("${gstr7.file.location}")
	protected String gstr7FileLocation;

	@Value("${gstr8.file.location}")
	protected String gstr8FileLocation;

	@Value("${gstr98a.file.location}")
	protected String gstr98aFileLocation;

	@Value("${gstr9c.file.location}")
	protected String gstr9cFileLocation;

	@Value("${gstr9.file.location}")
	protected String gstr9FileLocation;

	@Value("${itc2.file.location}")
	protected String itc2FileLocation;

	@Value("${pmt.file.location}")
	protected String pmtFileLocation;

	@Value("${gstr2.file.location}")
	protected String gstr2FileLocation;

	@Value("${gstr3.file.location}")
	protected String gstr3FileLocation;

	@Value("${gstr11.file.location}")
	protected String gstr11FileLocation;

	@Value("${gstr1r3b.file.location}")
	protected String gstr1r3bFileLocation;

	@Value("${gstr9a.file.location}")
	protected String gstr9aFileLocation;

	@Value("${gstr2b.file.location}")
	protected String gstr2bFileLocation;

	@Value("${gstr5a.file.location}")
	protected String gstr5aFileLocation;

	@Value("${payment.file.location}")
	protected String paymentFileLocation;

	@Value("${search-tax.payer.file.location}")
	protected String searchTaxPayerFileLocation;

	@Value("${normal-compostion.file.location}")
	protected String normalCompositionFileLocation;

	@Value("${json-file-path-normal-compostion.file.location}")
	protected String jsonFilePathNormalComposition;

	@Value("${tds-tcs.file.location}")
	protected String tdsTcsFileLocation;

	@Value("${file.dir.adjudication}")
	protected String adjudicationFileLocation;

	@Value("${document.file.location}")
	protected String registrationDocumentFileLocation;

	@Value("${file.dir.enforcement}")
	protected String enforcementFileLocation;

	@Value("${file.dir}")
	protected String gstr2aFileLocation;

	@Value("${ewayBill.file.gz.file}")
	protected String ewayBillFileLocation;

	@Value("${ewayBill.file.gz.file.extracted}")
	protected String ewayBillExtractedFileLocation;

	protected static final int BATCH_SIZE = 3; // Adjust the batch size as needed
	protected static final int THREAD_POOL_SIZE = 20; // Adjust the number of threads as needed

	// ========================= CONSTANTS =========================

	protected static final int HARD_MAX_RETRY = 30;
	protected static final int IO_BUFFER_SIZE = 8192;

	// ========================= DIRECTORY RESOLVER =========================
	protected String resolveDirectoryPath(String application) {

		// log.info("▶ Resolving directory path for application: {}", application);

		if (application == null || application.trim().isEmpty()) {
			// log.warn("⚠ Application is null or empty. Using default CMP08 directory
			// path.");
			return cmp8FileLocation;
		}

		switch (application.toLowerCase()) {

		case "cmp08":
			return cmp8FileLocation;

		case "gstr1":
			return gstr1FileLocation;

		case "gstr1a":
			return gstr1AFileLocation;

		case "gstr2b":
			return gstr2bFileLocation;

		case "gstr3b":
			return gstr3bFileLocation;

		case "gstr4":
			return gstr4FileLocation;

		case "gstr5":
			return gstr5FileLocation;

		case "gstr7":
			return gstr7FileLocation;

		case "gstr8":
			return gstr8FileLocation;

		case "gstr9":
			return gstr9FileLocation;

		case "gstr9c":
			return gstr9cFileLocation;

		case "gstr9a":
			return gstr9aFileLocation;

		case "gstr98a":
			return gstr98aFileLocation;

		case "gstr10":
			return gstr10FileLocation;

		case "gstr11":
			return gstr11FileLocation;

		case "itc02":
			return itc2FileLocation;

		case "payment":
			return paymentFileLocation;

		case "ewaybill":
			return ewayBillFileLocation;

		case "ewaybill-extracted":
			return ewayBillExtractedFileLocation;

		default:
			// log.warn("⚠ Unknown application: {} | using default CMP08 path",
			// application);
			return cmp8FileLocation;
		}
	}

//	public abstract String extractJsonFileNameToDb(String application);
//
//	public abstract String extractJsonFileNameToDbManual(String directoryPath, String application);
//
//	public abstract long saveDataFromJsonToDb(String application);
//
//	public abstract <T> Optional<T> findById(Long id, String application);
//
//	public abstract void deleteLastFolder(String application);
//
//	public abstract String deleteByDateAndApplication(String dt, String application);
//
//	public abstract boolean deleteById(Long id, String application);

}
