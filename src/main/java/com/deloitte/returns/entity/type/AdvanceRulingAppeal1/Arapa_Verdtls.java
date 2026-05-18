package com.deloitte.returns.entity.type.AdvanceRulingAppeal1;
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
@Table(name = "verdtls", schema = "advance_ruling_appeal")

public class Arapa_Verdtls {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("date")
	private String date;

	@JsonProperty("apnm")
	private String apnm;

	@JsonProperty("plc")
	private String plc;

	@JsonProperty("dsgn")
	private String dsgn;

	@JsonProperty("pan")
	private String pan;



}
