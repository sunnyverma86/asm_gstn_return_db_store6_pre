package com.deloitte.returns.entity.Gstr9c;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
@Table(name = "audited_data", schema = "gstr9c")
public class Gstr9c_AuditedData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("act_name")
	private String actName;

	@JsonProperty("arn")
	private String arn;

	@JsonProperty("arn_date")
	private String arnDate;

	@JsonProperty("designation")
	private String designation;

	@JsonProperty("fp")
	private String fp;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("lgl_name")
	private String lglName;

	@JsonProperty("signatoryname")
	private String signatoryname;

	@JsonProperty("trd_name")
	private String trdName;

	@JsonProperty("isauditor")
	private String isauditor;

	@JsonProperty("add_liab")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_liab_id")
	private Gstr9c_AddLiab addLiab;

	@JsonProperty("table10")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table10_id")
	private Gstr9c_Table10 table10;

	@JsonProperty("table11")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table11_id")
	private Gstr9c_Table11 table11;

	@JsonProperty("table12")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table12_id")
	private Gstr9c_Table12 table12;

	@JsonProperty("table13")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table13_id")
	private Gstr9c_Table13 table13;

	@JsonProperty("table14")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table14_id")
	private Gstr9c_Table14 table14;

	@JsonProperty("table15")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table15_id")
	private Gstr9c_Table15 table15;

	@JsonProperty("table16")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table16_id")
	private Gstr9c_Table16 table16;

	@JsonProperty("table5")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table5_id")
	private Gstr9c_Table5 table5;

	@JsonProperty("table6")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private Gstr9c_Table6 table6;

	@JsonProperty("table7")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table7_id")
	private Gstr9c_Table7 table7;

	@JsonProperty("table8")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table8_id")
	private Gstr9c_Table8 table8;

	@JsonProperty("table9")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table9_id")
	private Gstr9c_Table9 table9;
	
	@JsonProperty("tax_pay_9c")  //new add
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "audited_data_id")
    private List<Gstr9c_Taxpay9c> tax_pay_9c;
    
	@JsonProperty("tax_paid_9c")  //new add
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_9c_id")
    private Gstr9c_Taxpaid9c tax_paid_9c;



}
