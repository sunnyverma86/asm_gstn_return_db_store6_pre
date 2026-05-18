package com.deloitte.returns.entity.type.ProsecutionGetCaseData1;
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
@Table(name = "items", schema = "prosecution_case_data")

public class Prosc_Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("prosecNotce")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prosecNotce_id")
	private Prosc_prosecNotce prosecNotce;
	
	@JsonProperty("prosecInprOrdrs")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prosecInprOrdrs_id")
	private Prosc_ProsecInprOrdrs prosecInprOrdrs;

	@JsonProperty("prosecRjOrdrs")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prosecRjOrdrs_id")
	private Prosc_ProsecRjOrdrs prosecRjOrdrs;

	

}
