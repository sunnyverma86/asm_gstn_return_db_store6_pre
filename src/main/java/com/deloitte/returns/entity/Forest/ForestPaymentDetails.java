package com.deloitte.returns.entity.Forest;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_details", schema = "analytics")
@Data
public class ForestPaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_details_seq")
	@SequenceGenerator(name = "payment_details_seq", sequenceName = "analytics.payment_details_seq", allocationSize = 1)
	private Long id;

	private String stakeholder;
	private String pan;
	private String gstNo;
	private String dfoGstNo;
	private String paymentType;
	private BigDecimal amount;
	private LocalDate paymentDate;
}
