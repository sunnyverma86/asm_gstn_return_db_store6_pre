package com.deloitte.returns.entity.Gstr9c;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gstr9cdata", schema = "gstr9c")
public class Gstr9c_Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("audited_sign")
	@Column(length = 4000)
	private String auditedSign;

	@JsonProperty("audited_data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "audited_data_id")
	private Gstr9c_AuditedData auditedData;

	@JsonProperty("certificate")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "certificate_id")
	private Gstr9c_Certificate certificate;

}
