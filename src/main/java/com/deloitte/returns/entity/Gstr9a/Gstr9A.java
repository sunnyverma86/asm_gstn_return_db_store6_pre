package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "gstr9a", schema = "gstr9a")
@Entity
public class Gstr9A {

	//
	@Column(name = "return_file_count_primary_id")
	private Long returnFileCountPrimaryId;

	@Column(name = "return_file_detail_primary_id")
	private Long returnFileDetailPrimaryId;
//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cmp_frmdt")
	private String cmpFrmdt;

	@JsonProperty("cmp_todt")
	private String cmpTodt;

	@JsonProperty("dg")
	private String dg;

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("isnil")
	private String isnil;

	@JsonProperty("name")
	private String name;

	@JsonProperty("arn_dt")
	private String arn_dt;

	@JsonProperty("arn")
	private String arn;

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

	@JsonProperty("table10")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table10_id")
	private Gstr9a_Table10 table10;

	@JsonProperty("table14")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table14_id")
	private Gstr9a_Table14 table14;

	@JsonProperty("table15")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table15_id")
	private Gstr9a_Table15 table15;

	@JsonProperty("table16")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table16_id")
	private Gstr9a_Table16 table16;

	@JsonProperty("table5")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table5_id")
	private Gstr9a_Table5 table5;

	@JsonProperty("table6")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private Gstr9a_Table6 table6;

	@JsonProperty("table7")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table7_id")
	private Gstr9a_Table7 table7;

	@JsonProperty("table8")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table8_id")
	private Gstr9a_Table8 table8;

	@JsonProperty("table9")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table9_id")
	private Gstr9a_Table9 table9;

	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private Gstr9a_TaxPaid taxPaid;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9a_id")
	private List<Gstr9a_TaxPay> taxPay;

}