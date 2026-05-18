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
@Table(name = "table5", schema = "gstr9c")
public class Gstr9c_Table5 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("adj_dta")
	private Double adjDta;

	@JsonProperty("adj_turn_fef")
	private Double adjTurnFef;

	@JsonProperty("adj_turn_othrsn")
	private Double adjTurnOthrsn;

	@JsonProperty("adj_turn_sec")
	private Double adjTurnSEC;

	@JsonProperty("annul_turn_adj")
	private double annulTurnAdj;

	@JsonProperty("annul_turn_decl")
	private double annulTurnDecl;

	@JsonProperty("crd_note_acc")
	private Double crdNoteAcc;

	@JsonProperty("crd_nts_issued")
	private Double crdNtsIssued;

	@JsonProperty("dmd_sup")
	private Double dmdSup;

	@JsonProperty("trd_dis")
	private Double trdDis;

	@JsonProperty("turnovr")
	private double turnovr;

	@JsonProperty("turnovr_apr_jun")
	private double turnovrAPRJun;

	@JsonProperty("turnovr_comp")
	private Double turnovrComp;

	@JsonProperty("unadj_adv_beg")
	private Double unadjAdvBeg;

	@JsonProperty("unadj_adv_end")
	private Double unadjAdvEnd;

	@JsonProperty("unbil_rev_beg")
	private Double unbilRevBeg;

	@JsonProperty("unbil_rev_end")
	private Double unbilRevEnd;

	@JsonProperty("unrec_turnovr")
	private double unrecTurnovr;

}
