package com.deloitte.returns.entity.type.AppealRevisionOrders1;
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
@Table(name = "sgst", schema = "appeal_revision_order")

public class Rvord_Sgst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("tx")
	private Double tx;
	
	@JsonProperty("fee")
	private Double fee;

	@JsonProperty("pen")
	private Double pen;

	@JsonProperty("intr")
	private Double intr;

	@JsonProperty("oth")
	private Double oth;
	
	@JsonProperty("tot")
	private Double tot;



}
