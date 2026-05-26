package com.deloitte.returns.entity.registration;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"RegularTaxpayerAutomatic\"", schema = "gst_api_registration")
public class RegularTaxpayerAutomatic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"")
	private Long id;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "authstatus")
	private String authstatus;

	@Column(name = "apprvdt")
	private LocalDate apprvdt;

}
