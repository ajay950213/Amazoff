package com.rest_api.amazoff.exceptions;

public class LastDeliveryDateNotFoundException extends RuntimeException{

	public LastDeliveryDateNotFoundException() {
		super();
	}

	public LastDeliveryDateNotFoundException(String message) {
		super(message);
	}

}
