package com.deloitte.returns.entity.registration;

import java.sql.Date;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reg_documents", schema = "gst_api_registration")
public class RegDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"Id\"")
	private Long id;

	@Column(name = "gstin", length = 20)
	private String gstin;

	@Column(name = "apprvdt")
	private Date apprvdt;

	@Column(name = "reg_section", length = 30)
	private String regSection;

	@Column(name = "section_type", length = 30)
	private String sectionType;

	@Column(name = "eid", length = 10)
	private String eid;

	@Column(name = "ct", length = 50)
	private String ct;

	@Column(name = "document_id", length = 100)
	private String documentId;

	@Column(name = "ty", length = 100)
	private String ty;

	@Column(name = "hash", length = 200)
	private String hash;

	@Column(name = "addr_id", length = 50)
	private String addrId;

	@Column(name = "existing_doc", length = 20)
	private String existingDoc;

	@Column(name = "docttl", length = 100)
	private String docttl;

	@Column(name = "docname", length = 100)
	private String docname;

	@Column(name = "yy", length = 10)
	private String yy;

	@Column(name = "mm", length = 10)
	private String mm;

	@Column(name = "dd", length = 10)
	private String dd;

	@Column(name = "is_success")
	private Boolean isSuccess;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "error_msg")
	private JsonNode errorMsg;

	@Column(name = "file_path", length = 200)
	private String filePath;

	@Column(name = "file_name", length = 100)
	private String fileName;

	@Column(name = "insert_dt")
	private Date insertDt;

	@Column(name = "log_id")
	private Long logId;
}