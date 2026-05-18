package com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "liabDetl", schema = "enforcement_officer_gstr4")


public class En_Gstr4_LiabDetl {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    
    @JsonProperty("rev")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rev_id")
    public En_Gstr4_Rev rev;  

    
    @JsonProperty("nonRev")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nonRev_id")
    public En_Gstr4_NonRev nonRev;  



}
