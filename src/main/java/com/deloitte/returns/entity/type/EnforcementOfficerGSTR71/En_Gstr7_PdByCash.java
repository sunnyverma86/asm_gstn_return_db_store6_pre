package com.deloitte.returns.entity.type.EnforcementOfficerGSTR71;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "pd_by_cash", schema = "enforcement_officer_gstr7")


public class En_Gstr7_PdByCash {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;  // FIXED



	    @JsonProperty("igst")
	    @JsonPropertyDescription("IGST amount paid")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "igst_id")
	    public En_Gstr7_Igst igst;

	    @JsonProperty("sgst")
	    @JsonPropertyDescription("SGST amount paid")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "sgst_id")
	    public En_Gstr7_Sgst sgst;

	    @JsonProperty("cgst")
	    @JsonPropertyDescription("CGST amount paid")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cgst_id")
	    public En_Gstr7_Cgst cgst;

	    @JsonProperty("cess")
	    @JsonPropertyDescription("Cess amount paid")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cess_id")
	    public En_Gstr7_Cess cess;

	    @JsonProperty("liab_id")
	    @JsonPropertyDescription("Liability identifier")
	    @Column(name = "liab_id")
	    public Double liabId;

	    @JsonProperty("debit_id")
	    @JsonPropertyDescription("Debit number")
	    @Column(name = "debit_id")
	    public String debitId;
	    
	    
	    @JsonProperty("trancd")
	    public Integer trancd;
	    
	    @JsonProperty("trandate")
	    public String trandate;



}
