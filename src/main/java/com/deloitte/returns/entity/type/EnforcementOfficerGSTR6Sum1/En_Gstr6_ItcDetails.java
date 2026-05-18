package com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1;
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
@Table(name = "itcDetails", schema = "enforcement_officer_gstr6")


public class En_Gstr6_ItcDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("ret_period")
    private String retperiod;

    @JsonProperty("totalItc")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "totalItc_id")
    public En_Gstr6_TotalItc totalItc;

    @JsonProperty("elgitc")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "elgitc_id")
    public En_Gstr6_Elgitc elgitc;

    @JsonProperty("inelgitc")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inelgitc_id")
    public En_Gstr6_Inelgitc inelgitc;
    
    @JsonProperty("isdItcCross")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isdItcCross_id")
    public En_Gstr6_IsdItcCross isdItcCross;



}
