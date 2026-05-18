package com.deloitte.returns.entity.Gstr1A;

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
@Table(name = "urp2ba", schema = "gstr1a")
@Entity


public class Gstr1A_Urp2ba {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
