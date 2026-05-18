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
@Table(name = "userdtl", schema = "appeal_by_tax_payer")

public class Appeal_Userdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("enttyp")
	private String enttyp;
	
	@JsonProperty("entnum")
	private String entnum;
	
	@JsonProperty("legnm")
	private String legnm;

	@JsonProperty("trdnm")
	private String trdnm;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("status")
	private String status;




}
