package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "pd_by_nls", schema = "gstr9")
@Entity
public class Gstr9_Pdbynls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


}
