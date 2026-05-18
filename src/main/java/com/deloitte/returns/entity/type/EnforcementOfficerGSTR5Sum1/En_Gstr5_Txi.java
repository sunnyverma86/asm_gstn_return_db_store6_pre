package com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
@Table(name = "txi", schema = "enforcement_officer_gstr5")


public class En_Gstr5_Txi {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("liab")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "txi_id")   
    public List<En_Gstr5_Liab> liab;

    @JsonProperty("diffItc")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "txi_id")   
    public List<En_Gstr5_DiffItc> diffItc;


}
