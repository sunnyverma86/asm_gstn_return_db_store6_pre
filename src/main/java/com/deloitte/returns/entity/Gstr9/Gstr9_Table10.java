package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table10", schema = "gstr9")
@Entity
public class Gstr9_Table10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("dbn_amd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dbn_amd_id")
	private Gstr9_DbnAMD dbnAMD;

	@JsonProperty("itc_availd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_availd_id")
	private Gstr9_ItcAvaild itcAvaild;

	@JsonProperty("itc_rvsl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_rvsl_id")
	private Gstr9_ItcRvsl itcRvsl;

	@JsonProperty("total_turnover")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_turnover_id")
	private Gstr9_TotalTurnover totalTurnover;

	@JsonProperty("cdn_amd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdn_amd_id")
	private Gstr9_CDNAMD cdnAMD;

}