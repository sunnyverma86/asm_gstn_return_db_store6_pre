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
@Table(name = "rev", schema = "enforcement_officer_gstr4")

public class En_Gstr4_Rev {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("sgst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sgst_id")
    public En_Gstr4_Sgst sgst;
    
    @JsonProperty("cgst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cgst_id")
    public En_Gstr4_Cgst cgst;
    
    @JsonProperty("cess")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cess_id")
    public En_Gstr4_Cess cess;
    
    @JsonProperty("igst")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "igst_id")
    public En_Gstr4_Igst igst;
    
    @JsonProperty("tran_cd")
    public Integer trancd;




}
