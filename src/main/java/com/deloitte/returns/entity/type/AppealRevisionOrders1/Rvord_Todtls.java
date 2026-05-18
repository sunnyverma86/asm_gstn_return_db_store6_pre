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
@Table(name = "todtls", schema = "appeal_revision_order")

public class Rvord_Todtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("dg")
	private String dg;
	
	@JsonProperty("signty")
	private String signty;

	@JsonProperty("pl")
	private String pl;
	
	@JsonProperty("pn")
	private String pn;
	
	@JsonProperty("toid")
	private String toid;

	@JsonProperty("nm")
	private String nm;
	
	@JsonProperty("jurscd")
	private String jurscd;



}
