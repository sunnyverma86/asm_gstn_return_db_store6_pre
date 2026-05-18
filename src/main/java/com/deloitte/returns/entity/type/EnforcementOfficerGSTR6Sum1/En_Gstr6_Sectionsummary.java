package com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sectionsummary", schema = "enforcement_officer_gstr6")


public class En_Gstr6_Sectionsummary {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("sec_nm")
    private String secnm;
    
    @JsonProperty("chksum")
    public String chksum;
    
	@JsonProperty("rc")
	@Column(name = "rc")
	public Double rc;
	
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
	
    @JsonProperty("cpty_sum")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionsummary_id")   
    public List<En_Gstr6_Cptysum> cptysum;


}
