package com.deloitte.returns.entity.DownloadDocument;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dcupdtls_gstr9c", schema = "document")
public class DcupdtlsGstr9c {

	@Id
	@Column(name =  "\"Id\"")
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "fp")
	private String fp;

	@Column(name = "fil_dt")
	private LocalDate filDt;

	@Column(name = "doc_type")
	private String docType;

	@Column(name = "doc_id")
	private String docId;

	@Column(name = "doc_nam")
	private String docNam;

	@Column(name = "log_id")
	private Long logId;

	@Column(name = "is_active")
	private Boolean isActive;

	// Getters and Setters

	@Column(name = "is_processed")
	private Boolean isProcessed;
	
	@Column(name = "path")
	private String path;

	@Column(name = "file_name", length = 100)
	private String fileName;

	@Column(name = "insert_dt")
	private Date insertDt;
	
	
}