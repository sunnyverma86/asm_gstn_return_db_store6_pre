package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSTAuthenticationInputBean {
	
	public String action;
	public String username;
	public String password;
	public String appKey;
}
