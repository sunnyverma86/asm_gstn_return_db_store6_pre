package com.deloitte.returns.entity.Itc2;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itc02", schema = "itc02")
@Entity
public class Itc2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@JsonProperty("c_accept_flag")
	private String cAcceptFlag;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("caDetails")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "caDetails_id")
	private CADetails caDetails;

	@JsonProperty("itc_bal_sys")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_bal_sys_id")
	private ItcBALSys itcBALSys;

	@JsonProperty("itc_bal_usr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_bal_usr_id")
	private ItcBALUSR itcBALUSR;

}