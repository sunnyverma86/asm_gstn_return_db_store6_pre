package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;
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
@Table(name = "data", schema = "enforcement_officer_gstr1")

public class En_Gstr1_DATA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("num")
	private long num;
	
    @JsonProperty("iamt")//
    private Double iamt;
    
	@JsonProperty("rt")//
	private Double rt;

    @JsonProperty("csamt")//
    private Double csamt;
    
	@JsonProperty("samt")//
	private Double samt;
	
	
    @JsonProperty("camt")//
    private Double camt;
    

    @JsonProperty("txval")//
    private Double txval;
    
	@JsonProperty("qty")//
	private Double qty;

	@JsonProperty("hsn_sc")//
	private String hsn_sc;
	
	@JsonProperty("desc")//
	private String desc;

	@JsonProperty("uqc")//
	private String uqc;



	
	
	
}
