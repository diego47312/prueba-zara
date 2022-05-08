package com.zara.price.domain;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4702920905030609819L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}