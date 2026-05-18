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
@Table(name = "table7", schema = "gstr9a")
@Entity
public class Gstr9a_Table7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("b2b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private Gstr9a_B2B b2B;

	@JsonProperty("b2bur")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "_b2burid")
	private Gstr9a_B2Bur b2Bur;

	@JsonProperty("imps")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imps_id")
	private Gstr9a_Imps imps;

	@JsonProperty("tot")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_id")
	private Gstr9a_Tot tot;

}