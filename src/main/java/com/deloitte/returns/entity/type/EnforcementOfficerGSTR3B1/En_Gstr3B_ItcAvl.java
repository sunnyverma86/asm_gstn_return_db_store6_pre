package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

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
@Table(name = "itc_avl", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_ItcAvl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("iamt")
    private double iamt;
	
	@JsonProperty("camt")
    private double camt;
	
	@JsonProperty("samt")
    private double samt;
	
	@JsonProperty("csamt")
    private double csamt;
	
	@JsonProperty("ty")
    private String ty;
	
	@JsonProperty("txval")
    private Long txval;

}