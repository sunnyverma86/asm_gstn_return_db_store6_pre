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
@Table(name = "table10", schema = "gstr9a")
@Entity
public class Gstr9a_Table10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("b2b_cn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_cn_id")
	private Gstr9a_B2BCN b2BCN;

	@JsonProperty("b2b_dn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_dn_id")
	private Gstr9a_B2BDN b2BDN;

	@JsonProperty("tot_trnovr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_trnovr_id")
	private Gstr9a_TotTrnovr totTrnovr;

	@JsonProperty("txosa_cn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "txosa_cn_id")
	private Gstr9a_TxosaCN txosaCN;

	@JsonProperty("txosa_dn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "txosa_dn_id")
	private Gstr9a_TxosaDN txosaDN;

}