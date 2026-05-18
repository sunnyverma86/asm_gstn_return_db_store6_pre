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
@Table(name = "table12", schema = "gstr9c")
public class Gstr9c_Table12 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	

	@JsonProperty("itc_avail")
	private double itcAvail;

	@JsonProperty("itc_avail_audited")
	private double itcAvailAudited;

	@JsonProperty("itc_book_curr")
	private double itcBookCurr;

	@JsonProperty("itc_book_earl")
	private double itcBookEarl;

	@JsonProperty("itc_claim")
	private double itcClaim;

	@JsonProperty("unrec_itc")
	private double unrecItc;

}
