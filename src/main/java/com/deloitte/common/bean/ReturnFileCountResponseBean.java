package com.deloitte.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ReturnFileCountResponseBean {

	public String num_files;
	public String eod_closed;
	public String date;

	public Long returnFileCountId;

}
