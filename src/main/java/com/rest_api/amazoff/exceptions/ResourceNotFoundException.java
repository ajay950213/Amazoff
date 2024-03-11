package com.rest_api.amazoff.exceptions;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s ",resourceName,fieldName,fieldValue));
	}
	
	public ResourceNotFoundException(String message) {
        super(message);
	}

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}