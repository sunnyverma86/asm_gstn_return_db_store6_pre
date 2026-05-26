
package com.deloitte.returns.entity.Gstr8;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstin", "fp", "fil_dt", "tcs", "urd", "tcsa", "urda", "tax_pay", "tax_paid" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "gstr8", schema = "gstr8")
@Data
public class Gstr8 implements Serializable {

	//
	@Column(name = "return_file_count_primary_id")
	private Long returnFileCountPrimaryId;

	@Column(name = "return_file_detail_primary_id")
	private Long returnFileDetailPrimaryId;
//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin")

	@Column
	public String gstin;

	@JsonProperty("fp")

	@Column
	public String fp;

	@JsonProperty("fil_dt")

	@Column
	public String filDt;
	/**
	 * TCS details
	 * 
	 */
	@JsonProperty("tcs")
	@JsonPropertyDescription("TCS details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_Tc> tcs = new ArrayList<Gstr8_Tc>();

	@JsonProperty("urd")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_Urd> urd = new ArrayList<Gstr8_Urd>();

	@JsonProperty("tcsa")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_Tcsa> tcsa = new ArrayList<Gstr8_Tcsa>();

	@JsonProperty("urda")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_Urda> urda = new ArrayList<Gstr8_Urda>();
	/**
	 * Tax payable details
	 * 
	 */
	@JsonProperty("tax_pay")
	@JsonPropertyDescription("Tax payable details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_TaxPay> taxPay = new ArrayList<Gstr8_TaxPay>();
	/**
	 * Tax paid details
	 * 
	 */
	@JsonProperty("tax_paid")
	@JsonPropertyDescription("Tax paid details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr8_id")
	public List<Gstr8_TaxPaid> taxPaid;

	private final static long serialVersionUID = 7244810104316738376L;

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
