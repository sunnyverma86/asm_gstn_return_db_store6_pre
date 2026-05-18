package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "dtramt", schema = "appeal_by_tax_payer")

public class Appeal_Dtramt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("intr")
	private String intr;

	@JsonProperty("oth")
	private String oth;

	@JsonProperty("tx")
	private String tx;

	@JsonProperty("fee")
	private String fee;

	@JsonProperty("pen")
	private String pen;

	@JsonProperty("tot")
	private String tot;
	
	@JsonProperty("stcd")
	private String stcd;


}
