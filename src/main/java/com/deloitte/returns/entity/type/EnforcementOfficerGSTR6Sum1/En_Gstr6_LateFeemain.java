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
@Table(name = "lateFeemain", schema = "enforcement_officer_gstr6")


public class En_Gstr6_LateFeemain {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @JsonProperty("latefee")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "latefee_id")
    public En_Gstr6_Latefee latefee;


}
