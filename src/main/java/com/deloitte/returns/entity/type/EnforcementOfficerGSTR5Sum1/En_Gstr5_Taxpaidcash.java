package com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1;
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
@Table(name = "taxpaidcash", schema = "enforcement_officer_gstr5")


public class En_Gstr5_Taxpaidcash {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("sgst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sgst_id")
    public En_Gstr5_Sgst sgst;

    @JsonProperty("cgst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cgst_id")
    public En_Gstr5_Cgst cgst;  

    @JsonProperty("cess")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cess_id")
    public En_Gstr5_Cess cess;

    @JsonProperty("igst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "igst_id")
    public En_Gstr5_Igst igst;  
    
    @JsonProperty("debit_id")
    public String debitid;

    @JsonProperty("trandate")
    public String trandate;
    
    @JsonProperty("liab_id")
    public Integer liabid;

    @JsonProperty("trancd")
    public Integer trancd;



}
