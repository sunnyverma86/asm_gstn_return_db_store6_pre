package com.deloitte.returns.entity.DownloadDocument;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regis_dcupdtls_testing_0512", schema = "download_document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisDcupdtlsTesting {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@Column(name = "json_id_dcupdtls")
	private Long jsonIdDcupdtls;

	@Column(name = "registra_nor_tax_payer_id")
	private Long registraNorTaxPayerId;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "bzdtlsbz_bzdtls_dcupdtls_registra_nor_tax_payer_id")
	private Long bzdtlsbzBzdtlsDcupdtlsRegistraNorTaxPayerId;

	@Column(name = "bzdtlsbz_bzdtls_dcupdtls_gstin")
	private String bzdtlsbzBzdtlsDcupdtlsGstin;

	@Column(name = "bzdtls_tbl_id")
	private Long bzdtlsTblId;

	@Column(name = "bzdtlsbz_tbl_id")
	private Long bzdtlsbzTblId;

	@Column(name = "legal_name")
	private String legalName;

	@Column(name = "trade_name")
	private String tradeName;

	@Column(name = "bzdtlsbz_bzdtls_dcupdtls_dcupdtls_id")
	private Long bzdtlsbzBzdtlsDcupdtlsDcupdtlsId;

	@Column(name = "addrid")
	private Long addrid;

	@Column(name = "ct")
	private String ct;

	@Column(name = "hash")
	private String hash;

	@Column(name = "ty")
	private String ty;

	@Column(name = "ppbzdtls_id")
	private Long ppbzdtlsId;

	@Column(name = "opdtls_id")
	private Long opdtlsId;

	@Column(name = "bzdtlsbz_id")
	private Long bzdtlsbzId;

	@Column(name = "asgdtls_id")
	private Long asgdtlsId;

	@Column(name = "adbzdtls_id")
	private Long adbzdtlsId;

	@Column(name = "existing_doc")
	private String existingDoc;

	@Column(name = "ppbzdtls_tbl_id")
	private Long ppbzdtlsTblId;

	@Column(name = "ppbzdtls_dcupdtls_dcupdtls_id")
	private Long ppbzdtlsDcupdtlsDcupdtlsId;

	@Column(name = "bkacdtls_tbl_id")
	private Long bkacdtlsTblId;

	@Column(name = "bkacdtls_dcupdtls_dcupdtls_id")
	private Long bkacdtlsDcupdtlsDcupdtlsId;

	@Column(name = "asgdtls_tbl_id")
	private Long asgdtlsTblId;

	@Column(name = "asgdtls_dcupdtls_dcupdtls_id")
	private Long asgdtlsDcupdtlsDcupdtlsId;

	@Column(name = "opdtls_tbl_id")
	private Long opdtlsTblId;

	@Column(name = "opdtls_dcupdtls_dcupdtls_id")
	private Long opdtlsDcupdtlsDcupdtlsId;
	
	@Column
	private Boolean foundInfo = true;

	@Column
	private Boolean isProcessed = false;

	
}
