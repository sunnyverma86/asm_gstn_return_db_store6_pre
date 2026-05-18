package com.deloitte.returns.entity.type.AdvanceRulingAppeal1;
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
@Table(name = "arapadjrn", schema = "advance_ruling_appeal")

public class Arapa_Arapadjrn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemName")
	private String itemName;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("arapadjrndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arapadjrndata_id")
	private Arapa_Arapadjrndata arapadjrndata;


}
