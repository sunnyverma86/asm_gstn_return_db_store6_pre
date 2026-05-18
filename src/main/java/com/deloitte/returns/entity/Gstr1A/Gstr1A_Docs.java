package com.deloitte.returns.entity.Gstr1A;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "docs", schema = "gstr1a")
@Entity
public class Gstr1A_Docs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cancel")
	private Long cancel;

	@JsonProperty("from")
	private String fromData;

	@JsonProperty("net_issue")
	private Long netIssue;

	@JsonProperty("num")
	private Long num;

	@JsonProperty("to")
	private String toData;

	@JsonProperty("totnum")
	private Long totnum;

}
