package com.zara.price.application.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldError {

	private String field;
	private String errorCode;
	private String errorMessage;

}
