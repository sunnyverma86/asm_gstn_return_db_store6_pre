package com.deloitte.common.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSTCommonResponseBean {
	
	public String status_cd;
	public Map<String, String> error;
	public String data;
	public String rek;
	public String hmac;
}
