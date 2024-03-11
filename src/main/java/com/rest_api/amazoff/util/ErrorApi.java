package com.rest_api.amazoff.util;

import java.util.Date;

public class ErrorApi {

	private int errorCode;
	private String errorDescription;
	private Date date;
	private String path;
	
	

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path=path;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ErrorApi() {
		super();
	}

	public ErrorApi(int errorCode, String errorDescription, Date date) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.date = date;
	}

	@Override
	public String toString() {
		return "ErrorApi [errorCode=" + errorCode + ", errorDescription=" + errorDescription + ", date=" + date + "]";
	}

	

}
