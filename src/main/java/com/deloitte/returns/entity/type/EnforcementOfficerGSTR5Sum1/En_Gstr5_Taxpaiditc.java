package com.deloitte.returns.entity.type.EnforcementOfficerGSTR5Sum1;
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
@Table(name = "taxpaiditc", schema = "enforcement_officer_gstr5")


public class En_Gstr5_Taxpaiditc {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @JsonProperty("debit_id")
    public String debitid;
    
    @JsonProperty("liab_id")
    public Integer liabid;

    @JsonProperty("trancd")
    public Integer trancd;
    
    @JsonProperty("trandate")
    public String trandate;
    
    @JsonProperty("igst_igst_amt")
    public Integer igst_igst_amt;

    @JsonProperty("igst_cgst_amt")
    public Integer igst_cgst_amt;

    @JsonProperty("igst_sgst_amt")
    public Integer igst_sgst_amt;

    @JsonProperty("sgst_sgst_amt")
    public Integer sgst_sgst_amt;
    
    @JsonProperty("sgst_igst_amt")
    public Integer sgst_igst_amt;

    @JsonProperty("cgst_cgst_amt")
    public Integer cgst_cgst_amt;

    @JsonProperty("cgst_igst_amt")
    public Integer cgst_igst_amt;
    
    @JsonProperty("cess_cess_amt")
    public Integer cess_cess_amt;


}
