package com.deloitte.returns.entity.Refund;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cgst", schema = "refund")
public class Refund_Cgst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("adjamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adjamt_id")
	private Refund_Adjamt adjamt;

	@JsonProperty("clmamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clmamt_id")
	private Refund_Clmamt clmamt;

	@JsonProperty("grsamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grsamt_id")
	private Refund_Grsamt grsamt;

	@JsonProperty("inadmamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inadmamt_id")
	private Refund_Inadmamt inadmamt;

	@JsonProperty("netamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "netamt_id")
	private Refund_Netamt netamt;

	@JsonProperty("provamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "provamt_id")
	private Refund_Provamt provamt;

	@JsonProperty("rcvrdamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rcvrdamt_id")
	private Refund_Rcvrdamt rcvrdamt;

	@JsonProperty("intdlyamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intdlyamt_id")
	private Refund_Intdlyamt intdlyamt;

	@JsonProperty("netsancamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "netsancamt_id")
	private Refund_Netsancamt netsancamt;
	
	@JsonProperty("cwfamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cwfamt_id")
	private Refund_Cwfamt cwfamt;
	
	
	@JsonProperty("fee")
	private double fee;

	@JsonProperty("intr")
	private double intr;

	@JsonProperty("oth")
	private double oth;

	@JsonProperty("pen")
	private double pen;

	@JsonProperty("tot")
	private double tot;

	@JsonProperty("tx")
	private double tx;
	
	@JsonProperty("tax")
	private double tax;
	
	@JsonProperty("ldgrrecreditamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ldgrrecreditamt_id")
    private Refund_ldgrrecreditamt ldgrrecreditamt;


}