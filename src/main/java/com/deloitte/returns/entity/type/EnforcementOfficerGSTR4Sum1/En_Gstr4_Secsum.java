package com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "secsum", schema = "enforcement_officer_gstr4")

public class En_Gstr4_Secsum {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @JsonProperty("sec_nm")
    public String secnm;
    
    @JsonProperty("chksum")
    public String chksum;
    
    @JsonProperty("ttl_rec")
    public Integer ttlrec;

    @JsonProperty("ttl_val")
    public Integer ttlval;

    @JsonProperty("ttl_tax")
    public Integer ttltax;
    
    @JsonProperty("ttl_igst")
    public Integer ttligst;

    @JsonProperty("ttl_sgst")
    public Integer ttlsgst;

    @JsonProperty("ttl_cgst")
    public Integer ttlcgst;
    
    @JsonProperty("ttl_cess")
    public Integer ttlcess;
    
    @JsonProperty("ttl_liab")
    public Integer ttlliab;

    @JsonProperty("gross_adv_pd")
    public Integer grossadvpd;

    @JsonProperty("gross_adv_adj")
    public Integer grossadvadj;

    @JsonProperty("ttl_rchrg")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ttlrchrg_id")
    public En_Gstr4_Ttlrchrg ttlrchrg;
    
    @JsonProperty("ttl_non_rchrg")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ttlnonrchrg_id")
    public En_Gstr4_Ttlnonrchrg ttlnonrchrg;
    
    @JsonProperty("cpty_sum")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "secsum_id")   
    public List<En_Gstr4_Cptysum> cptysum;

	
	

}
