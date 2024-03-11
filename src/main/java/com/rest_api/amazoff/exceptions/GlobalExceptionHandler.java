package com.rest_api.amazoff.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.rest_api.amazoff.util.ErrorApi;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = DeliveryPartnerNotFoundException.class)
	public ResponseEntity<ErrorApi> handleDeliveryPartrnerNotFoundException(DeliveryPartnerNotFoundException del, WebRequest web) {

		ErrorApi error = new ErrorApi();
		Date date = new Date();
		error.setErrorCode(404);
		error.setDate(date);
		error.setErrorDescription(del.getMessage());
		String path = web.getDescription(false);
		error.setPath(path);
		return new ResponseEntity<ErrorApi>(error, HttpStatus.NOT_FOUND);

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = OrderNotFoundException.class)
	public Map<String, String> handleOrderNotFoundException(OrderNotFoundException ord, WebRequest web) {

		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("errorMessage", ord.getMessage());
		errorMap.put("errorCode", "404");
		errorMap.put("path", web.getDescription(false));

		return errorMap;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = LastDeliveryDateNotFoundException.class)
	public Map<String, String> lastDeliveryDateNotFoundException(LastDeliveryDateNotFoundException ex, WebRequest web) {

		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorCode", "404");
		errorMap.put("path", web.getDescription(false));
		errorMap.put("errorMessage", ex.getMessage());
		
		return errorMap;
	}

	@ExceptionHandler(AmazonException.class)
	public ResponseEntity<ErrorApi> amazonException(AmazonException ex, WebRequest web) {

		ErrorApi errorApi = new ErrorApi();
		errorApi.setDate(new Date());
		errorApi.setErrorDescription(ex.getMessage());
		String path = web.getDescription(false);
		errorApi.setPath(path);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorApi> amazonException(AccessDeniedException ex, WebRequest web) {
		ErrorApi errorApi = new ErrorApi();
		errorApi.setDate(new Date());
		errorApi.setErrorDescription(ex.getMessage());
		String path = web.getDescription(false);
		errorApi.setPath(path);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorApi);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorApi> resourceNotFoundException(ResourceNotFoundException ex, WebRequest web) {
		ErrorApi errorApi = new ErrorApi();
		errorApi.setDate(new Date());
		errorApi.setErrorCode(404);
		errorApi.setErrorDescription(ex.getMessage());
		String path = web.getDescription(false);
		errorApi.setPath(path);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorApi);

	}
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ErrorApi> internalAuthenticationServiceException(InternalAuthenticationServiceException ex, WebRequest web) {
		ErrorApi errorApi = new ErrorApi();
		errorApi.setDate(new Date());
		errorApi.setErrorCode(400);
		errorApi.setErrorDescription(ex.getMessage());
		String path = web.getDescription(false);
		errorApi.setPath(path);
		System.out.println(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);

	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorApi> badCredentialException(BadCredentialsException ex, WebRequest web) {
		ErrorApi errorApi = new ErrorApi();
		errorApi.setDate(new Date());
		errorApi.setErrorCode(400);
		errorApi.setErrorDescription(ex.getMessage());
		String path = web.getDescription(false);
		errorApi.setPath(path);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);

	}
	
}
