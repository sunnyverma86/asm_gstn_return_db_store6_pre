package com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1;
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
@Table(name = "liab", schema = "enforcement_officer_gstr5")


public class En_Gstr5_Liab {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @JsonProperty("rt")
    public Integer rt;

    @JsonProperty("igst")
    public Integer igst;

    @JsonProperty("sgst")
    public Integer sgst;

    @JsonProperty("cgst")
    public Integer cgst;

    @JsonProperty("cess")
    public Integer cess;

    @JsonProperty("txval")
    public Integer txval;



}
