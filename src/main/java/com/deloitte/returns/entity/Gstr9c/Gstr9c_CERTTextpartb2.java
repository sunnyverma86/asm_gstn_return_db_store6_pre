package com.deloitte.returns.entity.Gstr9c;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cert_textpartb2", schema = "gstr9c")
public class Gstr9c_CERTTextpartb2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("audit_date")
	private String auditDate;

	@JsonProperty("member_no")
	private String memberNo;

	@JsonProperty("audit_typ")
	private String auditTyp;

	@JsonProperty("conducted_by")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conducted_by_id")
	private Gstr9c_ConductedBy conductedBy;

	@JsonProperty("qualifications")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_textpartb2_id")
	private List<Gstr9c_Qualifications> qualifications;

}
