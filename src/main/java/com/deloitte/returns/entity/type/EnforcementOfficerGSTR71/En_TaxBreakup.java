package com.deloitte.returns.entity.type.EnforcementOfficerGSTR71;
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
@Table(name = "tax_breakup", schema = "enforcement_officer_gstr7")


public class En_TaxBreakup {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonProperty("tx")
    private Double tax;

    @JsonProperty("intr")
    private Double interest;

    @JsonProperty("fee")
    private Double fee;

    @JsonProperty("pen")
    private Double penalty;

    @JsonProperty("oth")
    private Double others;

    @JsonProperty("tot")
    private Double total;

}
