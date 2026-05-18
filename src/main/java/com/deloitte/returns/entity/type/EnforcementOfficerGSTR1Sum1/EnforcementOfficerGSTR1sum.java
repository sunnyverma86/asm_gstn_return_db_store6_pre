package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1Sum1;
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
@Table(name = "enforcement_officer_gstr1", schema = "enforcement_officer_gstr1_sum")


public class EnforcementOfficerGSTR1sum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("gstin")
    private String gstin;
    
    @JsonProperty("ret_period")
    private String ret_period;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("newSumFlag")
    private String newSumFlag;

    @JsonProperty("sec_sum")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr1_id")   
    public List<En_Gstr1_Secsum> secsum;


}
