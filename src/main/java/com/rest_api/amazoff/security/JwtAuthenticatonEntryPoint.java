package com.rest_api.amazoff.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//1st is adding jwt maven dependencies -> over
//2nd step is below in JWT Authentication -> over
//This class is for handle exception and return error response when unauthorizerd access is done(then we will get exception to hanlde that)
@Component
public class JwtAuthenticatonEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// configuring this to send error response
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

	}

}
