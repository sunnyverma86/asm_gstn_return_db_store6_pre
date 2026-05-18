package com.deloitte.returns.entity.Gstr10;

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
@Table(name = "addr", schema = "gstr10")
@Entity
public class Gstr10_Addr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("st")
	private String st;

	@JsonProperty("loc")
	private String loc;

	@JsonProperty("bno")
	private String bno;

	@JsonProperty("stcd")
	private String stcd;

	@JsonProperty("dst")
	private String dst;

	@JsonProperty("mbno")
	private String mbno;

	@JsonProperty("em")
	private String em;

	@JsonProperty("pncd")
	private String pncd;

	@JsonProperty("state")
	private String state;
	
	@JsonProperty("bnm")
	private String bnm;
	
	
	@JsonProperty("lt")
	private String lt;
	
	@JsonProperty("flno")
	private String flno;
	
	@JsonProperty("lg")
	private String lg;
	
	@JsonProperty("stdCdTl")
	private String stdCdTl;
	
	@JsonProperty("tlNo")
	private String tlNo;
	
	
	
	
	
	
	

}