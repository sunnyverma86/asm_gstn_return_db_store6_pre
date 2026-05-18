package com.deloitte.returns.entity.Gstr1A;

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
@Table(name = "gstr1a", schema = "gstr1a")
@Entity
public class Gstr1A {

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


	@JsonProperty("at")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_At> at;

	@JsonProperty("ata")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_ATA> ata;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2B> b2B;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2Ba> b2Ba;

	@JsonProperty("b2cl")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2Cl> b2Cl;

	@JsonProperty("b2cla")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2Cla> b2Cla;

	@JsonProperty("b2cs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2C> b2CS;

	@JsonProperty("b2csa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_B2Csa> b2Csa;

	@JsonProperty("cdnr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Cdnr> cdnr;

	@JsonProperty("cdnra")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Cdnra> cdnra;

	@JsonProperty("cdnur")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Cdnur> cdnur;

	@JsonProperty("cdnura")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Cdnura> cdnura;

	@JsonProperty("doc_issue")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doc_issue_id")
	public Gstr1A_DocIssue docIssue;

	@JsonProperty("hsn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hsn_id")
	private Gstr1A_Hsn hsn;

	@JsonProperty("nil")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nil_id")
	private Gstr1A_Nil nil;

	@JsonProperty("exp")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Exp> exp;

	@JsonProperty("expa")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Expa> expa;

	@JsonProperty("txpd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Txpd> txpd;

	@JsonProperty("txpda")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr1_id")
	public List<Gstr1A_Txpda> txpda;

	@JsonProperty("cur_gt")
	private Double curGt;

	@JsonProperty("fil_dt")
	private String filDt;

	@JsonProperty("fileIndex")
	private Long fileIndex;

	@JsonProperty("filing_typ")
	private String filingTyp;

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("gt")
	private Double gt;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("totalFiles")
	private Long totalFiles;

	@JsonProperty("version")
	private String version;

	@JsonProperty("isnil") // new
	private String isnil;

	@JsonProperty("supecoa") // new
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supecoa_id")
	private Gstr1A_Supecoa supecoa;

	@JsonProperty("supeco")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supeco_id")
	private Gstr1A_Supeco supeco;

	@JsonProperty("ecoma")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecoma_id")
	private Gstr1A_Ecoma ecoma;

	@JsonProperty("ecom")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ecom_id")
	private Gstr1A_Ecom ecom;

}
