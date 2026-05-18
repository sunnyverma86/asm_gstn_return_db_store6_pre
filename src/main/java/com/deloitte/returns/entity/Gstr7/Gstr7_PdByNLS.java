package com.deloitte.returns.entity.Gstr7;

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
@Table(name = "pd_by_nls", schema = "gstr7")
@Entity
public class Gstr7_PdByNLS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
