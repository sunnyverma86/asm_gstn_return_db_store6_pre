
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ret_period", "profile", "duedate", "aato_fy", "not_name", "not_dt", "txpay", "rates" })

@Entity
@Data
@Table(name = "interestbreakup", schema = "gstr3b")
public class Gstr3b_Interestbreakup implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// new changes start assam
	@JsonProperty("min_cashldg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "min_cashldg_id")
	private Gstr3b_MinCashldg min_cashldg;

	@JsonProperty("liab_intrchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "liab_intrchrg_id")
	private Gstr3b_liabIntrchrg liab_intrchrg;
	// new changes end assam

	@JsonProperty("ret_period")
	@Column
	private String retPeriod;
	@JsonProperty("profile")
	@Column
	private String profile;
	@JsonProperty("duedate")
	@Column
	private String duedate;
	@JsonProperty("aato_fy")
	@Column
	private String aatoFy;
	@JsonProperty("not_name")
	@Column
	private String notName;
	@JsonProperty("not_dt")
	@Column
	private String notDt;

	@JsonProperty("txpay")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "txpay_id")
	private Gstr3b_Txpay txpay;

	@JsonProperty("rates")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "interestbreakup_id")
	private List<Gstr3b_Rate> rates = new ArrayList<Gstr3b_Rate>();

	@JsonProperty("challan")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "interestbreakup_id")
	private List<Gstr3b_Challan> challan = new ArrayList<Gstr3b_Challan>();

	private final static long serialVersionUID = -5592015425505885800L;

}
