package com.deloitte.returns.entity.Gstr7;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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

@Entity
@Table(name = "gstr7", schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstin", "fp", "fil_dt", "gstr2aTds", "gstr2aTdsa", "tax_pay", "tax_paid" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7 {

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

	@JsonProperty("gstin")
	@Column(name = "gstin")
	public String gstin;

	@JsonProperty("fp")
	@Column(name = "fp")
	public String fp;

	@JsonProperty("fil_dt")
	@Column(name = "fil_dt")
	public String filDt;

	@JsonProperty("tds")
	@JsonPropertyDescription("TDS details")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr7_id")
	public List<Gstr7_Td> tds;

	@JsonProperty("tdsa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr7_id")
	public List<Gstr7_Tdsa> tdsa;

	@JsonProperty("tax_pay")
	@JsonPropertyDescription("Tax payable details")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr7_id")
	public List<Gstr7_TaxPay> taxPay;

	@JsonProperty("tax_paid")
	@JsonPropertyDescription("Tax paid details")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	public Gstr7_TaxPaid taxPaid;

}
