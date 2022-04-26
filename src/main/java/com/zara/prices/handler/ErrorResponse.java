package com.zara.prices.handler;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

	private Integer httpStatus;
	private String exception;
	private String message;
	private List<FieldError> fieldErrors;

}
