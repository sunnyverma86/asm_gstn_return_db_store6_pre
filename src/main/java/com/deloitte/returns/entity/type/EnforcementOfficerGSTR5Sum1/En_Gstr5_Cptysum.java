package com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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
@Table(name = "cptysum", schema = "enforcement_officer_gstr5")


public class En_Gstr5_Cptysum {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("ctin")
    private String ctin;
    
    @JsonProperty("chksum")
    public String chksum;
    
	@JsonProperty("rc")
	@Column(name = "rc")
	public Double rc;

	@JsonProperty("ttl_val")
	@Column(name = "ttlval")
	public Double ttlval;

	@JsonProperty("ttl_tax")
	@Column(name = "ttltax")
	public Double ttltax;

	@JsonProperty("ttl_txpd_igst")
	@Column(name = "ttltxpdigst")
	public Double ttltxpdigst;

	@JsonProperty("ttl_txpd_sgst")
	@Column(name = "ttltxpdsgst")
	public Double ttltxpdsgst;

	@JsonProperty("ttl_txpd_cgst")
	@Column(name = "ttltxpdcgst")
	public Double ttltxpdcgst;

	
	@JsonProperty("ttl_txpd_cess")
	@Column(name = "ttltxpdcess")
	public Double ttltxpdcess;
	

}
