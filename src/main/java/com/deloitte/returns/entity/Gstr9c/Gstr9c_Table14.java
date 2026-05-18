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
@Table(name = "table14", schema = "gstr9c")
public class Gstr9c_Table14 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "table14_id")
	private List<Gstr9c_Item> items;

	@JsonProperty("itc_claim")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_claim_id")
	private Gstr9c_ItcClaim itcClaim;

	@JsonProperty("tot_elig_itc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tot_elig_itc_id")
	private Gstr9c_Tot_elig_itc totEligItc;

	@JsonProperty("unrec_itc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "unrec_itc_id")
	private Gstr9c_UnrecItc unrecItc;

}
