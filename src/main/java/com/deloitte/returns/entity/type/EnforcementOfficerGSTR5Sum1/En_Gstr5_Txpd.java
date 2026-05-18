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
@Table(name = "txpd", schema = "enforcement_officer_gstr5")

public class En_Gstr5_Txpd {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("tax_pay")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "txpd_id")   
    public List<En_Gstr5_Taxpay> taxpay;

    @JsonProperty("tax_paidcash")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "txpd_id")   
    public List<En_Gstr5_Taxpaidcash> taxpaidcash;

    @JsonProperty("tax_paiditc")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "txpd_id")   
    public List<En_Gstr5_Taxpaiditc> taxpaiditc;


}
