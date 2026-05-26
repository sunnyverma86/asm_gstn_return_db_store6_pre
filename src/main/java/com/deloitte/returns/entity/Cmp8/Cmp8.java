package com.deloitte.returns.entity.Cmp8;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Entity
@Table(name = "cmp08", schema = "cmp08")
public class Cmp8 {

	//
	@Column(name = "return_file_count_primary_id")
	private Long returnFileCountPrimaryId;

	@Column(name = "return_file_detail_primary_id")
	private Long returnFileDetailPrimaryId;
//

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

	@JsonProperty("ret_period")
	private String retPeriod;

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("arn_dt")
	private String arnDt;

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("isnil")
	private String isnil;

	@JsonProperty("table3")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table3_id")
	private Cmp8_Table3 table3;

	@JsonProperty("table4")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table4_id")
	private Cmp8_Table4 table4;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cmp8_id")
	private List<Cmp8_TaxPay> taxPay;

	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private Cmp8_TaxPaid taxPaid;

}