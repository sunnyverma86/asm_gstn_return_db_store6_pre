package com.deloitte.returns.entity.Refund;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rfdAppData", schema = "refund")
public class Refund_RfdAppData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("rsnSpec") // new
	@Column(length = 500)
	private String rsnSpec;

	@JsonProperty("autoFiling")
	private boolean autoFiling; // new

	@JsonProperty("bankAccNo")
	private String bankAccNo;

	@JsonProperty("rfdrsn")
	private String rfdrsn;

	@JsonProperty("statementType")
	private String statementType;

	@JsonProperty("totrfdamt")
	private String totrfdamt;

	@JsonProperty("totxprd")
	private String totxprd;

	@JsonProperty("declaration")
	private String declaration;

	@JsonProperty("frmtxprd")
	private String frmtxprd;

	@JsonProperty("statement")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "statement_id")
	private Refund_Statement statement;

	@JsonProperty("bnkdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
	private List<Refund_Bnkdtls> bnkdtls;

	@JsonProperty("refClaim")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "refClaim_id")
	private Refund_RefClaim refClaim;//

	@JsonProperty("stmntsummary")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stmntsummary_id")
	private Refund_Stmntsummary stmntsummary;//

	@JsonProperty("docdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
	private List<Refund_Docdtl> docdtls;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Refund_Decdtls decdtls;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
	private List<Refund_Suppdocs> suppdocs;//

	@JsonProperty("stmnt7edt")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
	private List<Refund_Stmnt7Edt> stmnt7Edt;//
	
	@JsonProperty("statutoryorderdetails")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "statutoryorderdetails_id")
	private Refund_Statutoryorderdetails statutoryorderdetails;
	
	
	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfdAppData_id")
    private List<Refund_Annxdocs> annxdocs;//
   
    @JsonProperty("supplierInfo")
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierInfo_id")
    private Refund_SupplierInfo supplierInfo;
    
    






}