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
@Table(name = "igst", schema = "enforcement_officer_gstr4")


public class En_Gstr4_Igst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("intr")
    public Integer intr;

    @JsonProperty("oth")
    public Integer oth;
    
    @JsonProperty("tx")
    public Integer tx;

    @JsonProperty("fee")
    public Integer fee;
    
    @JsonProperty("pen")
    public Integer pen;

    @JsonProperty("tot")
    public Integer tot;


}
