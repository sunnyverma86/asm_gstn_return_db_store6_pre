package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table9", schema = "gstr9")
@Entity
public class Gstr9_Table9 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;


	@JsonProperty("camt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camt_id")
	private Gstr9_Camt txpaidCamt;
	
	@JsonProperty("samt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "samt_id")
	private Gstr9_Samt txpaidSamt;

	@JsonProperty("csamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "csamt_id")
	private Gstr9_Csamt txpaidCsamt;

	@JsonProperty("fee")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fee_id")
	private Gstr9_Fee txpaidFee;

	@JsonProperty("iamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iamt_id")
	private Gstr9_Iamt txpaidIamt;

	@JsonProperty("intr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_id")
	private Gstr9_Intr txpaidIntr;

	@JsonProperty("other")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "other_id")
	private Gstr9_Other txpaidOther;

	@JsonProperty("pen")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pen_id")
	private Gstr9_Pen txpaidPen;

	

}