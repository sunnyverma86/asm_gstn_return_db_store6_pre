package com.deloitte.returns.entity.type.AppealRevisionOrders1;
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
@Table(name = "sdtls", schema = "appeal_revision_order")

public class Rvord_Sdtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("recpofnotce")
	private String recpofnotce;

	@JsonProperty("notcplace")
	private String notcplace;
	
	@JsonProperty("ntcsubj")
	private String ntcsubj;

	@JsonProperty("pht")
	private String pht;

	@JsonProperty("phdt")
	private String phdt;

	@JsonProperty("venu")
	private String venu;
	
	@JsonProperty("ordrplace")
	private String ordrplace;

	@JsonProperty("ordtyp")
	private String ordtyp;

	@JsonProperty("ordnum")
	private String ordnum;
	
	@JsonProperty("orddt")
	private String orddt;
	
	@JsonProperty("apeldt")
	private String apeldt;
	
	@JsonProperty("ordsts")
	private String ordsts;
	
	@JsonProperty("dispamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dispamt_id")
	private Rvord_Dispamt dispamt;

	@JsonProperty("dtramt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtramt_id")
	private Rvord_Dtramt dtramt;






}
