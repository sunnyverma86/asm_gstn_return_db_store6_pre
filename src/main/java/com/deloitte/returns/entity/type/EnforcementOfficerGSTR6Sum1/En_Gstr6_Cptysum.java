package com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1;
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
@Table(name = "cptysum", schema = "enforcement_officer_gstr6")

public class En_Gstr6_Cptysum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	@JsonProperty("ttl_txpd_sgst")
	@Column(name = "ttltxpdsgst")
	public Double ttltxpdsgst;

	@JsonProperty("ttl_txpd_igst")
	@Column(name = "ttltxpdigst")
	public Double ttltxpdigst;

	@JsonProperty("ttl_txpd_cess")
	@Column(name = "ttltxpdcess")
	public Double ttltxpdcess;

	@JsonProperty("ttl_txpd_cgst")
	@Column(name = "ttltxpdcgst")
	public Double rcttltxpdcgst;

	@JsonProperty("ttl_val")
	@Column(name = "ttlval")
	public Double ttlval;
	
    @JsonProperty("chksum")
    public String chksum;
    
    @JsonProperty("ctin")
    public String ctin;

	@JsonProperty("rc")
	@Column(name = "rc")
	public Double rc;


}
