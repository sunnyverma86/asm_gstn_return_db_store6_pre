package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "dispamt", schema = "appeal_by_tax_payer")

public class Appeal_Dispamt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("igst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "igst_id")
	private Appeal_Igst igst;

	@JsonProperty("cgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cgst_id")
	private Appeal_Cgst cgst;

	@JsonProperty("sgst")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sgst_id")
	private Appeal_Sgst sgst;

	@JsonProperty("cess")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cess_id")
	private Appeal_Cess cess;

	@JsonProperty("total")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "total_id")
	private Appeal_Total total;
	
	@JsonProperty("intr")
	private String intr;

	@JsonProperty("oth")
	private String oth;

	@JsonProperty("tx")
	private String tx;

	@JsonProperty("fee")
	private String fee;

	@JsonProperty("pen")
	private String pen;

	@JsonProperty("tot")
	private String tot;




}
