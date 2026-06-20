package com.deloitte.returns.entity.Forest;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_details", schema = "analytics")
@Data
public class ForestPaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String pan;
	private String gstNo;
	private String dfoGstNo;

	private BigDecimal amount;
	private LocalDate paymentDate;

	@Column(name = "module_name",length = 1000)
	private String moduleName;

	@Column(name = "stakeholder",length = 1000)
	private String stakeholder;

	@Column(name = "mineral_name",length = 1000)
	private String mineralName;

	@Column(name = "payment_type")
	private String paymentType;


	@Column(name = "cpin")
	private String cpin;;
}
