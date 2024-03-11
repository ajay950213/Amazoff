package com.rest_api.amazoff.requestdtos;

public class LoginDto {

	private String usernameOrEmail;
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDto() {
		super();
	}

}
