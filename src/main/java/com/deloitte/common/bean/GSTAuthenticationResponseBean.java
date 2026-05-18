package com.deloitte.common.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSTAuthenticationResponseBean {
	// @Contract(pure = true)
	public String status_cd;
	public String auth_token;
	public String sek;
	public Map<String, String> error;

	@Override
	public String toString() {
		return "GSTAuthenticationResponseBean{" + "status_cd='" + status_cd + '\'' + ", auth_token='" + auth_token
				+ '\'' + ", sek='" + sek + '\'' + ", error='" + error + '\'' + '}';
	}
}
