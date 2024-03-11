package com.rest_api.amazoff.service;

import com.rest_api.amazoff.requestdtos.LoginDto;
import com.rest_api.amazoff.requestdtos.RegisterDto;

public interface AuthService {

	String login(LoginDto loginDto);

	String register(RegisterDto registerUpDto);

}
