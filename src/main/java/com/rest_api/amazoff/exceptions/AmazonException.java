package com.rest_api.amazoff.exceptions;

import org.springframework.http.HttpStatus;

public class AmazonException extends RuntimeException{

	public AmazonException() {
		super();
	}

	public AmazonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AmazonException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmazonException(String message) {
		super(message);
	}
	
	public AmazonException(HttpStatus httpStatus, String message) {
		super(message);
	}

	public AmazonException(Throwable cause) {
		super(cause);
	}

}
