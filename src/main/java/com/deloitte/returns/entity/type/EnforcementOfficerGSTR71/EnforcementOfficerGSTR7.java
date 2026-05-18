package com.deloitte.returns.entity.type.EnforcementOfficerGSTR71;

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
@Table(name = "enforcement_officer_gstr7", schema = "enforcement_officer_gstr7")


public class EnforcementOfficerGSTR7 {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("fp")
    private String fp;
    
    @JsonProperty("arn")
    public String arn;

    @JsonProperty("arn_dt")
    public String arnDt;

    @JsonProperty("dg")
    public String dg;

    @JsonProperty("name")
    public String name;

    @JsonProperty("tds")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tds_id")
    public En_Gstr7_Tds tds;

    @JsonProperty("tdsa")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tdsa_id")
    public En_Gstr7_Tdsa tdsa;  

    @JsonProperty("tax_pay")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr7_id")   
    public List<En_Gstr7_TaxPay> taxPay;

    @JsonProperty("tax_paid")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_paid_id")
    public En_Gstr7_TaxPaid taxPaid;
}
