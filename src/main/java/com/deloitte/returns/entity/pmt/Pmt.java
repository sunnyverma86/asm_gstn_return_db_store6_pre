package com.deloitte.returns.entity.pmt;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pmt", schema = "pmt")
public class Pmt {

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

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("filingdt")
	private String filingdt;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("isnil")
	private String rtnprd;

	@JsonProperty("isnil")
	private String isnil;

	@JsonProperty("table3")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table3_id")
	private Pmt_Table3 table3;

	@JsonProperty("table4")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table4_id")
	private Pmt_Table4 table4;

}