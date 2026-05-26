package com.deloitte.returns.entity.type.RecoveryPmtInstallmentOrDeferredPmt;
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
@Table(name = "recovery_pmt_installment_deferredPmt", schema = "recovery_pmt_installment_deferred_pmt")

public class RecoveryPmtIntallmentDeferredPmt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;



}
