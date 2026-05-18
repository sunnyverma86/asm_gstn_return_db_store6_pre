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
@Table(name = "urd", schema = "enforcement_officer_gstr8")


public class En_Gstr8_Urd {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("gSuppRtn")
    public Double gSuppRtn;   
    
    @JsonProperty("no_rec")
    public Double norec;   

    @JsonProperty("gSuppMade")
    public Double gSuppMade;   

    @JsonProperty("ttl_amtcol")
    public Double ttlamtcol;   

 

}
