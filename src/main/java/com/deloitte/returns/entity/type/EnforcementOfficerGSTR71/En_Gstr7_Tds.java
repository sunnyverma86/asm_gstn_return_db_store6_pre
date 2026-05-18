package com.deloitte.returns.entity.type.EnforcementOfficerGSTR71;

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
@Table(name = "tds", schema = "enforcement_officer_gstr7")

public class En_Gstr7_Tds {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // FIXED

    @JsonProperty("ttl_igst")
    public Double ttlIgst;

    @JsonProperty("ttl_cgst")
    public Double ttlCgst;

    @JsonProperty("ttl_sgst")
    public Double ttlSgst;

    @JsonProperty("ttl_amtDed")
    public Double ttlAmtDed;

    @JsonProperty("no_rec")
    public Integer noRec;   

    @JsonProperty("no_gstin")
    public Integer noGstin;
	

}
