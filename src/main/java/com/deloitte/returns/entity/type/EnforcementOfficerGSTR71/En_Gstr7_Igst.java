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
@Table(name = "igst", schema = "enforcement_officer_gstr7")



public class En_Gstr7_Igst {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;



	@JsonProperty("tx")
	@Column(name = "tx")
	public Double tx;

	@JsonProperty("intr")
	@Column(name = "intr")
	public Double intr;

	@JsonProperty("pen")
	@Column(name = "pen")
	public Double pen;

	@JsonProperty("fee")
	@Column(name = "fee")
	public Double fee;

	@JsonProperty("oth")
	@Column(name = "oth")
	public Double oth;

	@JsonProperty("tot")
	@Column(name = "tot")
	public Double tot;

	


}
