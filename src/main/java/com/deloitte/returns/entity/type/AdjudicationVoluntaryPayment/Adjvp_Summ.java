package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;
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
@Table(name = "summ", schema = "adjudication_voluntary_payment")
public class Adjvp_Summ {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @JsonProperty("sgst")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "sgst_id")
	    private Adjvp_TaxSummary sgst;

	    @JsonProperty("cgst")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cgst_id")
	    private Adjvp_TaxSummary cgst;

	    @JsonProperty("igst")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "igst_id")
	    private Adjvp_TaxSummary igst;

	    @JsonProperty("cess")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "cess_id")
	    private Adjvp_TaxSummary cess;

	    @JsonProperty("total")
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "total_id")
	    private Adjvp_TaxSummary total;
	
	
}
