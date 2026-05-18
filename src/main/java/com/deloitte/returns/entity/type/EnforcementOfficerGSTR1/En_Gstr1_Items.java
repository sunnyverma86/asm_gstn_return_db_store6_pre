package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Entity
@Table(name = "items", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("num")
	private long num;
	
//    @JsonProperty("iamt")//
//    private Double iamt;
//    
//	@JsonProperty("rt")//
//	private long rt;
//
//	@JsonProperty("ad_amt")//
//	private long ad_amt;
//	
//	@JsonProperty("csamt")//
//	private long csamt;
//	
//	@JsonProperty("txval")//
//	private long txval;
//	
//	@JsonProperty("camt")//
//	private long camt;
//	
//	@JsonProperty("samt")//
//	private long samt;


	@JsonProperty("itm_det")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itm_det_id")
	private En_Gstr1_ItmDet itm_det;


}