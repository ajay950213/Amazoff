package com.rest_api.amazoff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_api.amazoff.requestdtos.LoginDto;
import com.rest_api.amazoff.requestdtos.RegisterDto;
import com.rest_api.amazoff.responsedtos.JwtAuthResponseDto;
import com.rest_api.amazoff.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	// Build login restAPI
	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto) {

//		String response = authService.login(loginDto);
		String token = authService.login(loginDto);

		JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
		jwtAuthResponseDto.setAccessToken(token);

//		return ResponseEntity.ok(token);
		return ResponseEntity.ok(jwtAuthResponseDto);

	}

	// Build register RestAPI
	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
