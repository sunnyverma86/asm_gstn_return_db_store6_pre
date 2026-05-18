package com.deloitte.returns.entity.Cmp8;

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
@Table(name = "pd_by_nls", schema = "cmp08")
public class Cmp8_PdByNLS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
