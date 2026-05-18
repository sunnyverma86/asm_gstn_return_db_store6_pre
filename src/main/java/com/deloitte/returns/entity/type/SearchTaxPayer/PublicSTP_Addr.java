package com.deloitte.returns.entity.type.SearchTaxPayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addr", schema = "search_tax_payer")
public class PublicSTP_Addr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bnm")
	private String bnm;

	@JsonProperty("st")
	private String st;

	@JsonProperty("loc")
	private String loc;

	@JsonProperty("bno")
	private String bno;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("flno")
	private String flno;

	@JsonProperty("lt")
	private String lt;

	@JsonProperty("lg")
	private String lg;

	@JsonProperty("pncd")
	private String pncd;
	

	@JsonProperty("dst")
    private String dst;
 
    @JsonProperty("locality")
    private String locality;

    @JsonProperty("landMark")
    private String landMark;

    @JsonProperty("geocodelvl")
    private String geocodelvl;


}