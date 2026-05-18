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
@Table(name = "aradvord", schema = "advance_ruling_taxpayer")

public class Arara_Aradvord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("aradvorddata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aradvorddata_id")
	private Arara_Aradvorddata aradvorddata;


}
