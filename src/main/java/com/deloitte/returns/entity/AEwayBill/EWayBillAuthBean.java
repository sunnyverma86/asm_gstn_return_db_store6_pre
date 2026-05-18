package com.deloitte.returns.entity.AEwayBill;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "auth", schema = "eway_bill_not")
public class EWayBillAuthBean {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authtoken;
	private String encSek;
	private String sek;
	private String status;
	private String errorDesc;

	private String dataDesc;
	private byte[] appKeyDb;

	private String appUrl;

	// 🔥 NEW FIELDS (Important)
	@Column(name = "token_expiry")
	private Timestamp tokenExpiry;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;
}
