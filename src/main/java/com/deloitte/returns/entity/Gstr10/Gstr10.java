package com.deloitte.returns.entity.Gstr10;

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
@Table(name = "gstr10", schema = "gstr10")
@Entity
public class Gstr10 {

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

	@JsonProperty("filingDt")
	private String filingDt;

	@JsonProperty("cal_odr_dt")
	private String calOdrDt;

	@JsonProperty("cal_odr_no")
	private String calOdrNo;

	@JsonProperty("cal_dt")
	private String calDt;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("hsn")
	private String hsn;

	@JsonProperty("addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private Gstr10_Addr addr;

	@JsonProperty("reg_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reg_pay_id")
	private Gstr10_RegPay regPay;

	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private Gstr10_TaxPaid taxPaid;

	@JsonProperty("attachments")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr10_id")
	private List<Gstr10_Attachment> attachments;

	@JsonProperty("doc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr10_id")
	private List<Gstr10_Doc> doc;

	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr10_id")
	private List<Gstr10_Inv> inv;

	@JsonProperty("itms_sum")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr10_id")
	private List<Gstr10_ItmsSum> itmsSum;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr10_id")
	private List<Gstr10_TaxPay> taxPay;

	// cal_odr_dt

}