package com.deloitte.returns.entity.Cmp8;

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
@Table(name = "table4", schema = "cmp08")
public class Cmp8_Table4 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonProperty("neg_liab")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "neg_liab_id")
	private Cmp8_NegLiab negLiab;

	@JsonProperty("intr_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_pay_id")
	private Cmp8_IntrPay intrPay;

	@JsonProperty("otr_rchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "otr_rchrg_id")
	private Cmp8_OtrRchrg otrRchrg;

	@JsonProperty("rchrg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rchrg_id")
	private Cmp8_Rchrg rchrg;

}