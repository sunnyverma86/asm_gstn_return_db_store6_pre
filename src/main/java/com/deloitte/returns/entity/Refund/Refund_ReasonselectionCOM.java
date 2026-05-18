package com.deloitte.returns.entity.Refund;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reasonselectionCom", schema = "refund")
public class Refund_ReasonselectionCOM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
