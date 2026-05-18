package com.deloitte.returns.entity.type.AppealHigherAuthority1;
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
@Table(name = "todtls", schema = "appeal_higher_authority")

public class Aplha_Todtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("toid")
	private String toid;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("dg")
	private String dg;
	
	@JsonProperty("nm")
	private String nm;
	
	@JsonProperty("signty")
	private String signty;

	@JsonProperty("pn")
	private String pn;
	
	@JsonProperty("pl")
	private String pl;



}
