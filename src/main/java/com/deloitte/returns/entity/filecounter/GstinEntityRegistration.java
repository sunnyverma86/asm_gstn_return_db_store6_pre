package com.deloitte.returns.entity.filecounter;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gstin_entity_Registration", schema = "filecounter")
@Data
public class GstinEntityRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String arnNumber;

	@Column
	private String gstinNumber;

	@Column
	private String alertCd;

	@Column
	private String endDateTime;

	@Column
	private Long alertId;
	
	@Column
	private Long arnDetailCommonRegistrationId;

	@Column
	private String taxpayerType;

	@Column
	private Boolean foundInfo = true;

	@Column
	private Boolean isProcessedGstr2a = false;

	@Column
	private Boolean isProcessedLedgerCash = false;

	@Column
	private Boolean isProcessedLedgerItc = false;

	@Column
	private Boolean isProcessedLedgerTax = false;

	@Column
	private Boolean isProcessedLedgerOther = false;

	@Column
	private Boolean isProcessedRegistration = false;

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

}
