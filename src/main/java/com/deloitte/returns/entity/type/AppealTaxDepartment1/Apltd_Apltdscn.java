package com.deloitte.returns.entity.type.AppealTaxDepartment1;
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
@Table(name = "apltdscn", schema = "appeal_by_tax_department")

public class Apltd_Apltdscn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("apltdscndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "apltdscndata_id")
	private Apltd_Apltdscndata apltdscndata;

	

}
