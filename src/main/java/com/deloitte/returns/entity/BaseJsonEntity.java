package com.deloitte.returns.entity;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.JsonNode;

public interface BaseJsonEntity {

	void setReturnFileDetailPrimaryId(Long id);

	void setReturnFileCountPrimaryId(Long id);

	void setFilePath(String filePath);

	void setJsonData(JsonNode jsonData);

	void setFileNumber(Integer fileNumber);

	void setSequenceNumber(Integer sequenceNumber);

	void setDt(Date dt);

	void setCategory(String category);

	void setInsertDt(Timestamp insertDt);

}