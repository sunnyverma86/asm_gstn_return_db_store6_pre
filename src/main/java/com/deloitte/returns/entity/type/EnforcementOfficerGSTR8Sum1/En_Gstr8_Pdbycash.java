package com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1;
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
@Table(name = "pdbycash", schema = "enforcement_officer_gstr8")


public class En_Gstr8_Pdbycash {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("debit_id")
    private String debitid;

    @JsonProperty("trandate")
    private String trandate;
    
    @JsonProperty("liab_id")
    private Integer liabid;
    
    @JsonProperty("trancd")
    public Integer trancd;   

    @JsonProperty("iamt")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iamt_id")
    public En_Gstr8_Iamt iamt;

    @JsonProperty("samt")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "samt_id")
    public En_Gstr8_Samt samt;

    @JsonProperty("camt")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "camt_id")
    public En_Gstr8_Camt camt;

}
