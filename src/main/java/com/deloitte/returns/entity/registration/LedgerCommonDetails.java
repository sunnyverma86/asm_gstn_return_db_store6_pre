package com.deloitte.returns.entity.registration;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ledger_common_details_cmn", schema = "asm")
public class LedgerCommonDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "authstatus")
	private String authstatus;

	@Column(name = "apprvdt")
	private LocalDate apprvdt;

	@Column(name = "from_dt")
	private LocalDate fromDate;

	@Column(name = "to_dt")
	private LocalDate toDate;

	@Column(name = "action")
	private String action;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "entityjson")
	private String entityJson;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "url")
	private String url;

	@Column(name = "msg")
	private String msg;
	
	@Column(name = "status")
	private String status;

	@Column(name = "create_date_time", updatable = false)
	private Instant createDateTime;

	@Column(name = "updated_date_time")
	private Instant updatedDateTime;

	@Column(name = "type_registration_msg") // entitytyp
	private String typeRegistrationMsg;

	@PrePersist
	protected void onCreate() {
		Instant now = Instant.now();
		this.createDateTime = now;
		this.updatedDateTime = now;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDateTime = Instant.now();

	}
}
