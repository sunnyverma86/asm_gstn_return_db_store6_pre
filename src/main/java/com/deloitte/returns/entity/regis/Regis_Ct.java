package com.deloitte.returns.entity.regis;

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
@Table(name = "ct", schema = "regis")
public class Regis_Ct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
