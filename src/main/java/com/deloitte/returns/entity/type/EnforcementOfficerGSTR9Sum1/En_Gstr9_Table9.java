package com.deloitte.returns.entity.type.EnforcementOfficerGSTR9Sum1;
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
@Table(name = "table9", schema = "enforcement_officer_gstr9")


public class En_Gstr9_Table9 {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("iamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iamt_id")
	private En_Gstr9_Iamt Iamt;
	
	@JsonProperty("camt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camt_id")
	private En_Gstr9_Camt Camt;
	
	@JsonProperty("samt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "samt_id")
	private En_Gstr9_Samt Samt;

	@JsonProperty("csamt")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "csamt_id")
	private En_Gstr9_Csamt Csamt;
	
	@JsonProperty("intr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_id")
	private En_Gstr9_Intr Intr;
	
	@JsonProperty("fee")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fee_id")
	private En_Gstr9_Fee Fee;
	
	@JsonProperty("pen")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pen_id")
	private En_Gstr9_Pen Pen;
	
	@JsonProperty("other")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "other_id")
	private En_Gstr9_Other Other;



}
