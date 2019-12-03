package com.zeeshan.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpError {

	private Integer errCode;
	private String errMsg;
	private Date date;

}
