package com.rest_api.amazoff.exceptions;

public class DeliveryPartnerNotFoundException extends RuntimeException {

	public DeliveryPartnerNotFoundException() {
		super("Delivery partner not found");
	}

	public DeliveryPartnerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeliveryPartnerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeliveryPartnerNotFoundException(String message) {
		super(message);
	}	

	public DeliveryPartnerNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
