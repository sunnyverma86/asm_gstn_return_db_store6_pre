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
@Table(name = "table9", schema = "gstr9c")
public class Gstr9c_Table9 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("rate")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table9_id")
	private List<Gstr9c_Rate> rate;

	@JsonProperty("inter")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inter_id")
	private Gstr9c_Inter inter;

	@JsonProperty("late_fee")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "late_fee_id")
	private Gstr9c_LateFee lateFee;

	@JsonProperty("oth")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "oth_id")
	private Gstr9c_Oth oth;

	@JsonProperty("pen")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pen_id")
	private Gstr9c_Pen pen;

	@JsonProperty("tot_amt_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_amt_paid_id")
	private Gstr9c_TotAmtPaid totAmtPaid;

	@JsonProperty("tot_amt_payable")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_amt_payable_id")
	private Gstr9c_TotAmtPayable totAmtPayable;

	@JsonProperty("unrec_amt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unrec_amt_id")
	private Gstr9c_UnrecAmt unrecAmt;
	
	@JsonProperty("oth_ecom")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "oth_ecom_id")
	private Gstr9c_OthEcom oth_ecom;


}
