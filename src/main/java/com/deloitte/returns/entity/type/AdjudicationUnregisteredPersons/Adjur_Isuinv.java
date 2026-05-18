package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
@Table(name = "isuinv", schema = "adjudication_unregistered_persons")

public class Adjur_Isuinv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @JsonValue
    @Column(name = "isuinv_code", length = 5)
    private String code;

    // 🔑 THIS FIXES THE ERROR
    @JsonCreator
    public Adjur_Isuinv(String code) {
        this.code = code;
    }
}
