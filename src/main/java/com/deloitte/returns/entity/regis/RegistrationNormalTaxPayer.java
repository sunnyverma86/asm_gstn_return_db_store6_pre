package com.deloitte.returns.entity.regis;

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
@Table(name = "registra_nor_tax_payer", schema = "regis")
public class RegistrationNormalTaxPayer {

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

	@JsonProperty("isMigrated")
	private String isMigrated;

	@JsonProperty("ntcrbs")
	private String ntcrbs;

	@JsonProperty("riskProfile")
	private String riskProfile;

	@JsonProperty("appr_auth")
	private String apprAuth;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("rfndRiskScore")
	private String rfndRiskScore;

	@JsonProperty("canc_dt")
	private String canc_dt;

	@JsonProperty("supdcdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	private List<Regis_Supdcdtls> supdcdtls;

	@JsonProperty("bzdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bzdtls_id")
	private Regis_Bzdtls bzdtls;

	@JsonProperty("adbzdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	private List<Regis_Adbzdtl> adbzdtls;

	@JsonProperty("ppbzdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ppbzdtls_id")
	private Regis_Ppbzdtls ppbzdtls;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Regis_Decdtls decdtls;

	@JsonProperty("bkacdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	private List<Regis_Bkacdtls> bkacdtls;

	@JsonProperty("asgdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	public List<Regis_Asgdtls> asgdtls;

	@JsonProperty("gdssvcdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gdssvcdtls_id")
	private Regis_Gdssvcdtls gdssvcdtls;

	@JsonProperty("opdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	public List<Regis_Opdtls> opdtls;

	@JsonProperty("arepdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_nor_tax_payer_id")
	private List<Regis_Arepdtls> arepdtls;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stsidtls_id")
	@JsonProperty("stsidtls")
	private Regis_Stsidtls stsidtls;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "registra_nor_tax_payer_id")
//	private List<Regis_Dcupdtls> dcupdtls;

}
