package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enforcement_officer_gstr9", schema = "enforcement_officer_gstr9")

public class EnforcementOfficerGSTR9 {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;

	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("fp")
    private String fp;
    
    @JsonProperty("aggTurnover")
    private Double aggTurnover;
   
	@JsonProperty("table4")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table4_id")
	private En_Gstr9_Table4 table4;

	@JsonProperty("table5")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table5_id")
	private En_Gstr9_Table5 table5;

	@JsonProperty("table6")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table6_id")
	private En_Gstr9_Table6 table6;

	@JsonProperty("table7")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table7_id")
	private En_Gstr9_Table7 table7;

	@JsonProperty("table8")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table8_id")
	private En_Gstr9_Table8 table8;

	@JsonProperty("table9")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table9_id")
	private En_Gstr9_Table9 table9;

	@JsonProperty("table10")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table10_id")
	private En_Gstr9_Table10 table10;

	@JsonProperty("table14")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table14_id")
	private En_Gstr9_Table14 table14;

	@JsonProperty("table15")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table15_id")
	private En_Gstr9_Table15 table15;

	@JsonProperty("table16")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table16_id")
	private En_Gstr9_Table16 table16;

	@JsonProperty("table17")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table17_id")
	private En_Gstr9_Table17 table17;

	@JsonProperty("table18")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "table18_id")
	private En_Gstr9_Table18 table18;
	
	@JsonProperty("tax_paid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private En_Gstr9_TaxPaid taxPaid;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr9_id")
	private List<En_Gstr9_TaxPay> taxPay;
	

}
