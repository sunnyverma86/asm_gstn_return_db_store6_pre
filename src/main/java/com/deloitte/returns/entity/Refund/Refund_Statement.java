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
@Table(name = "statement", schema = "refund")
public class Refund_Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("adjto")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adjto_id")
	private Refund_Adjto adjto;

	@JsonProperty("invto")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invto_id")
	private Refund_Invto invto;

	@JsonProperty("netitc/ttlItc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "netitc_ttlItc_id")
	private Refund_NetitcTTLItc netitcTTLItc;

	@JsonProperty("taxpybinv")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "taxpybinv_id")
	private Refund_Taxpybinv taxpybinv;

	@JsonProperty("zeroto")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "zeroto_id")
	private Refund_Zeroto zeroto;
	
	
	@JsonProperty("netITCBal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "netITCBal_id")
	private Refund_NetITCBal netITCBal;
	

}