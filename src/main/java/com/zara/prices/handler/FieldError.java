package com.zara.prices.handler;

import lombok.Data;

@Data
public class FieldError {

	private String field;
	private String errorCode;
	private String errorMessage;

}
