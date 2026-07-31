package com.deloitte.returns.entity.EwayBill;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eway_bill", schema = "eway_live_eway_bill_new")
public class EwayBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("Ver")
	private String ver;

	@JsonProperty("StateCode")
	private long stateCode;

	@JsonProperty("StateName")
	private String stateName;

	@JsonProperty("Category")
	private String category;

	@JsonProperty("ProcDate")
	private String procDate;
	
	@JsonProperty("Period")
	private String Period;

	@JsonProperty("ewb")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "eway_bill_id")
	private List<EwayBill_Ewb> ewb;

	// ✅ NEW FIELD
	@Column(name = "created_dtm")
	private LocalDateTime createdDtm;
	
	@Column(name = "id_eway_gz_json_storage")
	private Long idEwayGzJsonStorage;

}
