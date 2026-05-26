package com.deloitte.returns.entity.filecounter;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arn_detail_common_registration", schema = "filecounter")
public class ArnDetailCommonRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long returnAlertJsonId;

	@Column
	private String alertCd;

	@Column
	private String entityIdOrArn;

	@Column
	private String entityTyp;

	@Column
	private String insertTime;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "json_data")
	private JsonNode jsonData;

	@Column
	private String url;

	@Column
	private String statusType;

	@Column
	private String fileName;

	@Column
	private String folderPath;

	@Column
	private Boolean isProcessed;

	@Column
	private String startDateTime;

	@Column
	private String endDateTime;
	
	

}
