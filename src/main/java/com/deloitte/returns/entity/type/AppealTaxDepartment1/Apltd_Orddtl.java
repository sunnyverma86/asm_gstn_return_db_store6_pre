package com.deloitte.returns.entity.type.AppealTaxDepartment1;
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
@Table(name = "orddtl", schema = "appeal_by_tax_department")

public class Apltd_Orddtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("orddt")
	private String orddt;

	@JsonProperty("dmdid")
	private String dmdid;

	@JsonProperty("ordtyp")
	private String ordtyp;
	
	@JsonProperty("ordnum")
	private String ordnum;

	@JsonProperty("addr")
	private String addr;

	@JsonProperty("ordcommdt")
	private String ordcommdt;
	
	@JsonProperty("refundgranted")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "refundgranted_id")
	private Apltd_Refundgranted refundgranted;

	@JsonProperty("prdofdisp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prdofdisp_id")
	private Apltd_Prdofdisp prdofdisp;

	@JsonProperty("refundclaimed")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "refundclaimed_id")
	private Apltd_Refundclaimed refundclaimed;

	@JsonProperty("dispamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dispamt_id")
	private Apltd_Dispamt dispamt;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Apltd_Todtls todtls;




}
