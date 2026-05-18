package com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1;
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
@Table(name = "isdItcCross", schema = "enforcement_officer_gstr6")

public class En_Gstr6_IsdItcCross {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @JsonProperty("samt")
    private Double samt;

    @JsonProperty("csamt")
    private Double csamt;

    @JsonProperty("iamtc")
    private Double iamtc;
    
    @JsonProperty("iamts")
    private Double iamts;

    @JsonProperty("camti")
    private Double camti;
    
    @JsonProperty("samti")
    private Double samti;

    @JsonProperty("camt")
    private Double camt;

    @JsonProperty("samts")
    private Double samts;
    
    @JsonProperty("cess")
    private Double cess;
    
    @JsonProperty("camtc")
    private Double camtc;

    @JsonProperty("iamt")
    private Double iamt;
    
    @JsonProperty("iamti")
    private Double iamti;



}
