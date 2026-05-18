package com.deloitte.returns.entity.type.AppealTaxDepartment1;
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
@Table(name = "cess", schema = "appeal_by_tax_department")

public class Apltd_Cess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("oth")
	private Double oth;

	@JsonProperty("intr")
	private Double intr;

	@JsonProperty("fee")
	private Double fee;
	
	@JsonProperty("pen")
	private Double pen;

	@JsonProperty("tot")
	private Double tot;

	@JsonProperty("tax")
	private Double tax;

}
