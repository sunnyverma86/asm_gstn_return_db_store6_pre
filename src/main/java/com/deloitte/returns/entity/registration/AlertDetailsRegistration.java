package com.deloitte.returns.entity.registration;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"ALERT_DETAILS_REGISTRATION\"", schema = "gst_api_registration")
public class AlertDetailsRegistration {

//	@Id
//	@SequenceGenerator(name = "alert_details_registration_seq_generator", sequenceName = "gst_api_registration.alert_details_registration_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alert_details_registration_seq_generator")
	@Column(name = "\"ALERT_DETAIL_ID\"")//ok
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long alertDetailId;

	@Column(name = "\"ALERT_ID\"")//ok
	private Long alertId;

	@Column(name = "\"daycount\"")//ok
	private Integer dayCount;

	@Column(name = "\"alertcd\"")//ok
	private String alertCd;

	@Column(name = "\"entityid\"")//entityid
	private String entityId;

	@Column(name ="\"entitytyp\"")//entitytyp
	private String entityTyp;

	@Column(name ="insert_tm")//insert_tm
	private LocalDateTime insertTm;
	
	@Column(name ="\"issuccess\"")//issuccess
	private Boolean isSuccess;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name ="\"jsondata\"")
	private String jsonData;

	@Column(name = "isentitysuccess")
	private Boolean isEntitySuccess;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "entityjson")
	private String entityJson;


	@Column(name = "partition_fy")
	private LocalDate partitionFy;

	@Column(name = "create_date_time", updatable = false)
	private Instant createDateTime;

	@Column(name = "updated_date_time")
	private Instant updatedDateTime;
	
	@Column(name ="type_registration_msg")//entitytyp
	private String typeRegistrationMsg;
	
	@Column(name = "counter_attempt") //
	private int counterAttempt;


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




