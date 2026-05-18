package com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1;
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
@Table(name = "cptysum", schema = "enforcement_officer_gstr4")


public class En_Gstr4_Cptysum {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @JsonProperty("ctin")
    public String ctin;
    
    @JsonProperty("trade_name")
    public String trade_name;

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


}
