package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "table9", schema = "gstr9a")
@Entity
public class Gstr9a_Table9 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("tx_pay")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tx_pay_id")
	private Gstr9a_TxPay txPay;

	@JsonProperty("txpd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "txpd_id")
	private Gstr9a_Txpd txpd;

}