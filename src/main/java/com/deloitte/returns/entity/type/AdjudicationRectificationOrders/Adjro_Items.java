package com.deloitte.returns.entity.type.AdjudicationRectificationOrders;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items", schema = "adjudication_rectification_orders")
public class Adjro_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refids;

	@JsonProperty("refId")
	private String refId;

	@JsonProperty("roRectOrderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roRectOrderdata_id")
	private Adjro_RoRectOrderdata roRectOrderdata;

	@JsonProperty("roRejOrderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roRejOrderdata_id")
	private Adjro_RoRejOrderdata roRejOrderdata;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Adjro_Todtls todtls;

	@JsonProperty("roappdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roappdata_id")
	private Adjro_Roappdata roappdata;

	@JsonProperty("roNotice")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjro_RoNotice> roNotice;

	@JsonProperty("roReply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjro_RoReply> roReply;

}