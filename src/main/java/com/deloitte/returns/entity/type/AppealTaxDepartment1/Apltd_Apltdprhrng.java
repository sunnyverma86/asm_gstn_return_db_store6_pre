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
@Table(name = "apltdprhrng", schema = "appeal_by_tax_department")

public class Apltd_Apltdprhrng {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("apltdprhrngdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "apltdprhrngdata_id")
	private Apltd_Apltdprhrngdata apltdprhrngdata;
	


}
