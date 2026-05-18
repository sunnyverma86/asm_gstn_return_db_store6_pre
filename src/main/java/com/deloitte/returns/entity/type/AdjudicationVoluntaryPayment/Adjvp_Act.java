package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

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
@Entity
@Table(name = "act", schema = "adjudication_voluntary_payment")
public class Adjvp_Act {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long act_id;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("acttyp")
	private String acttyp;

	@JsonProperty("dbtdt")
	private String dbtdt;

	@JsonProperty("dbtno")
	private String dbtno;

	@JsonProperty("intr")
	private String intr;

	@JsonProperty("ldgrut")
	private String ldgrut;

	@JsonProperty("others")
	private String others;

	@JsonProperty("pnlty")
	private String pnlty;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("total")
	private String total;

	@JsonProperty("tx")
	private String tx;
	
	@JsonProperty("fees")
	private String fees;

	@JsonProperty("finyr")
	private String finyr;
	
	@JsonProperty("tp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tp_id")
	private Adjvp_Tp tp;

}