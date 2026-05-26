package com.deloitte.returns.entity.type.ProsecutionGetCaseData;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
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
@Table(name = "orders", schema = "prosecution_case_data")

public class Prosc_Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tpname")
	private String tpname;

	@JsonProperty("orderdate")
	private String orderdate;

	@JsonProperty("ordertype")
	private String ordertype;
	
	@JsonProperty("rejreason")
	private String rejreason;
	
	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orders_id")
	private List<Prosc_Maindocs> maindocs;




}
