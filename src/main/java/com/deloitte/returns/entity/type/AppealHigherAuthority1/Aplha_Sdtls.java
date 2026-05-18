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
@Table(name = "sdtls", schema = "appeal_higher_authority")

public class Aplha_Sdtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("ordnum")
	private String ordnum;

	@JsonProperty("orddt")
	private String orddt;
	
	@JsonProperty("commdt")
	private String commdt;

	@JsonProperty("ordsts")
	private String ordsts;

	@JsonProperty("ordtyp")
	private String ordtyp;



}
