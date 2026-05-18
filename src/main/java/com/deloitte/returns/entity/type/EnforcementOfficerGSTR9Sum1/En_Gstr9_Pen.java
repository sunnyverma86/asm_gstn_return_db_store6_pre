package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
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
@Table(name = "Pen", schema = "enforcement_officer_gstr9")



public class En_Gstr9_Pen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("tax_paid_itc_camt")
	private Double taxPaidItcCamt;

	@JsonProperty("tax_paid_itc_iamt")
	private Double taxPaidItcIamt;

	@JsonProperty("tax_paid_itc_samt")
	private Double taxPaidItcSamt;

	@JsonProperty("txpaid_cash")
	private Double txpaidCash;

	@JsonProperty("txpyble")
	private Double txpyble;
	
	@JsonProperty("txpaid")
	private Double txpaid;
	
	@JsonProperty("total_tax_paid")
	private Double totaltaxpaid;
	
	@JsonProperty("diff_tax_paid")
	private Double difftaxpaid;




}
