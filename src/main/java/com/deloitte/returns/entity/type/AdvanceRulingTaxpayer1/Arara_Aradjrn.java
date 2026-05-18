package com.deloitte.returns.entity.type.AdvanceRulingTaxpayer1;
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
@Table(name = "aradjrn", schema = "advance_ruling_taxpayer")

public class Arara_Aradjrn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("aradjrndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aradjrndata_id")
	private Arara_Aradjrndata aradjrndata;


}
