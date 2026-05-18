package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnFileDetailsResponseBean {
	public String cnt;
	public String file_num;
	public String url;
	public String hash;
}
