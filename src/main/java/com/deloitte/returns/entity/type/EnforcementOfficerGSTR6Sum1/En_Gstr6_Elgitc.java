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
@Table(name = "elgitc", schema = "enforcement_officer_gstr6")


public class En_Gstr6_Elgitc {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("csamt")
    private Integer csamt;

    @JsonProperty("samt")
    private Integer samt;

    @JsonProperty("camt")
    private Integer camt;

    @JsonProperty("iamt")
    private Integer iamt;


}
