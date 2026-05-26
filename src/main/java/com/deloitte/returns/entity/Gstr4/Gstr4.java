package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "gstr4", schema = "gstr4")
@Entity
public class Gstr4 {
	
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

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("cur_gt")
	private long curGt;

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("gt")
	private long gt;

	@JsonProperty("at")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_At> at;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_B2B> b2B;

	@JsonProperty("b2bur")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_B2Bur> b2Bur;

	@JsonProperty("cdnr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Cdnr> cdnr;

	@JsonProperty("cdnur")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Cdnur> cdnur;

	@JsonProperty("imp_s")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Imp> impS;

	@JsonProperty("txos")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Txo> txos;

	@JsonProperty("txpd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Txpd> txpd;

	@JsonProperty("imp_sa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_ImpSa> impSa;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_B2Ba> b2Ba;

	@JsonProperty("txosa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Txoa> txosa;

	@JsonProperty("b2bura")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_B2Bura> b2Bura;

	
	 @JsonProperty("tax_py_pd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_py_pd_id")
	private Gstr4_TaxPyPd taxPyPd;
	

	@JsonProperty("ata")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Ata> ata;

	@JsonProperty("cdnura")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Cdnura> cdnura;

	@JsonProperty("cdnra")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Cdnra> cdnra;

	@JsonProperty("txpda")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_Txpda> txpda;
	
	
	


}
