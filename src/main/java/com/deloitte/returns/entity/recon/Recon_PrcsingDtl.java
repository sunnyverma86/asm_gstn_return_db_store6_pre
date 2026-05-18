package com.deloitte.returns.entity.recon;

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
@Table(name = "prcsing_dtls", schema = "recon")
public class Recon_PrcsingDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("prcsing_dt")
	private String prcsingDt;

	@JsonProperty("rec_cnt_for_flg_dt")
	private Long recCntForFlgDt;

	@JsonProperty("tot_rec_cnt")
	private Long totRecCnt;

}
