package com.deloitte.common.bean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastUpdateDTO {

	private String ty;

	private LocalDate maxDate;

}
