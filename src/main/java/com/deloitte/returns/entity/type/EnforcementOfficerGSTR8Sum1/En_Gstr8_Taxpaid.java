package com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1;
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
@Table(name = "taxpaid", schema = "enforcement_officer_gstr8")


public class En_Gstr8_Taxpaid {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("pd_by_cash")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr8_id")   
    public List<En_Gstr8_Pdbycash> pdbycash;


}
