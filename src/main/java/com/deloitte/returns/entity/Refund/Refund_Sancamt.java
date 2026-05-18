package com.deloitte.returns.entity.Refund;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sancamt", schema = "refund")
public class Refund_Sancamt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@JsonProperty("cess")
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "cess_id")
//	private Refund_Cess cess;
//
//	@JsonProperty("cgst")
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "cgst_id")
//	private Refund_Cgst cgst;
//
//	@JsonProperty("igst")
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "igst_id")
//	private Refund_Igst igst;
//
//	@JsonProperty("sgst")
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "sgst_id")
//	private Refund_Sgst sgst;

	@JsonProperty("cess")
	private long cess;

	@JsonProperty("cgst")
	private long cgst;

	@JsonProperty("igst")
	private long igst;

	@JsonProperty("sgst")
	private long sgst;

	@JsonProperty("fee")
	private long fee;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("pen")
	private long pen;

	@JsonProperty("tax")
	private long tax;

	@JsonProperty("tot")
	private long tot;
}