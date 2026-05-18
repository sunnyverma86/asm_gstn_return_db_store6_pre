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
@Table(name = "orddtl", schema = "appeal_revision_order")

public class Rvord_Orddtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("dmdid")
	private String dmdid;

	@JsonProperty("ordnum")
	private String ordnum;
	
	@JsonProperty("ordtyp")
	private String ordtyp;

	@JsonProperty("ordcommdt")
	private String ordcommdt;
	
	@JsonProperty("orddt")
	private String orddt;
	
	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Rvord_Todtls todtls;





}
