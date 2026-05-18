package com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1;
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
@Table(name = "tcsa", schema = "enforcement_officer_gstr8")


public class En_Gstr8_Tcsa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("chksum")
    private String chksum;

    @JsonProperty("gSuppMade")
    private Double gSuppMade;
    
    @JsonProperty("gSuppRtn")
    private Double gSuppRtn;
    
    @JsonProperty("ttl_iamt")
    public Double ttliamt;   
    
    @JsonProperty("ttl_camt")
    private Double ttlcamt;
    
    @JsonProperty("ttl_samt")
    private Double ttlsamt;
    
    @JsonProperty("ttl_amtcol")
    private Double ttlamtcol;
    
    @JsonProperty("no_rec")
    private Double norec;
    
    @JsonProperty("no_gstin")
    private Double nogstin;



}
