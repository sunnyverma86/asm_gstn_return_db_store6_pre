package com.deloitte.returns.entity.Gstr10;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachments", schema = "gstr10")
@Entity
public class Gstr10_Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("docid")
	private String docid;

	@JsonProperty("hash")
	private String hash;

	@JsonProperty("ty")
	private String ty;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("caDetails")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "caDetails_id")
	private Gstr10_CADetails caDetails;

}