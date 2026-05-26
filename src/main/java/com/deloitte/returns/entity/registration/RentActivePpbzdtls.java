package com.deloitte.returns.entity.registration;

import java.sql.Date;

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
@Table(name = "rent_active_ppbzdtls", schema = "analytical_dashboard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentActivePpbzdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "reg_section")
	private String regSection;

	@Column(name = "section_type")
	private String sectionType;

	@Column(name = "document_id")
	private String documentId;

	@Column(name = "path")
	private String path;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "insert_dt")
	private Date insertDt;

	@Column(name = "file_name", length = 100)
	private String fileName;
	
	@Column(name = "ct")
	private String ct;
}
