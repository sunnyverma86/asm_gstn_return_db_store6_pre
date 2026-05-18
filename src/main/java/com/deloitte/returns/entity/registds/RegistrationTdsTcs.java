
package com.deloitte.returns.entity.registds;

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
@Table(name = "registra_tds_tcs", schema = "registds")
public class RegistrationTdsTcs {

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

	@JsonProperty("ntcrbs") // new
	private String ntcrbs;

	@JsonProperty("appr_auth") // new
	private String apprAuth;

	@JsonProperty("aplCd")
	public String aplCd;

	@JsonProperty("gstin")
	public String gstin;

	@JsonProperty("nba")
	public String nba;

	@JsonProperty("canc_dt")
	public String cancDt;

	@JsonProperty("riskProfile")
	public String riskProfile;

	@JsonProperty("rfndRiskScore")
	public String rfndRiskScore;

	@JsonProperty("appr_dt")
	public String apprDt;

	@JsonProperty("asgdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_tds_tcs_id")
	public List<TdsTcs_Asgdtl> asgdtls;

	@JsonProperty("bzdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bzdtls_id")
	public TdsTcs_Bzdtls bzdtls;

	@JsonProperty("bkacdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_tds_tcs_id")
	public List<TdsTcs_Bkacdtl> bkacdtls;

	@JsonProperty("ddo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ddo_id")
	public TdsTcs_Ddo ddo;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	public TdsTcs_Decdtls decdtls;

	@JsonProperty("ppbzdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ppbzdtls_id")
	public TdsTcs_Ppbzdtls ppbzdtls;

	@JsonProperty("rgdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rgdtls_id")
	public TdsTcs_Rgdtls rgdtls;

	@JsonProperty("supdcdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "registra_tds_tcs_id")
	public List<TdsTcs_Supdcdtl> supdcdtls;

}
