package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

import com.fasterxml.jackson.annotation.*;

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
@Table(name = "dtramt", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Dtramt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("dfees")
    private String dfees;
    
    @JsonProperty("dist")
    private String dist;
    
    @JsonProperty("dothers")
    private String dothers;
    
    @JsonProperty("dpnlty")
    private String dpnlty;
    
    @JsonProperty("dtax")
    private String dtax;
    
    @JsonProperty("dtot")
    private String dtot;
    
    @JsonProperty("pos")
    private String pos;

}
