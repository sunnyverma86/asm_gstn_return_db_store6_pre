package com.deloitte.returns.entity.Gstr9c;

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
@Table(name = "table7", schema = "gstr9c")
public class Gstr9c_Table7 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonProperty("annul_turn_adj")
	private double annulTurnAdj;

	@JsonProperty("othr_turnovr")
	private Double othrTurnovr;

	@JsonProperty("rev_sup")
	private Double revSup;

	@JsonProperty("tax_turn_adj")
	private double taxTurnAdj;

	@JsonProperty("tax_turn_annul")
	private double taxTurnAnnul;

	@JsonProperty("unrec_tax_turn")
	private double unrecTaxTurn;

	@JsonProperty("zero_sup")
	private Double zeroSup;

	@JsonProperty("rev_sup_ecom") //new add
	private Double rev_sup_ecom;

}
