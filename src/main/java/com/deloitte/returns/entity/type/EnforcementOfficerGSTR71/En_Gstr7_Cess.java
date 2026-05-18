package com.deloitte.returns.entity.type.EnforcementOfficerGSTR71;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
@Table(name = "cess", schema = "enforcement_officer_gstr7")


public class En_Gstr7_Cess {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @JsonProperty("tx")
	    @Column(name = "tx")
	    private Double tx;

	    @JsonProperty("intr")
	    @Column(name = "intr")
	    private Double intr;

	    @JsonProperty("pen")
	    @Column(name = "pen")
	    private Double pen;

	    @JsonProperty("fee")
	    @Column(name = "fee")
	    private Double fee;

	    @JsonProperty("oth")
	    @Column(name = "oth")
	    private Double oth;

	    @JsonProperty("tot")
	    @Column(name = "tot")
	    private Double tot;

}
