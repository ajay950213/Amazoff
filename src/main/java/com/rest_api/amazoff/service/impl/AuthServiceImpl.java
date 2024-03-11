package com.rest_api.amazoff.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest_api.amazoff.entity.Roles;
import com.rest_api.amazoff.entity.User;
import com.rest_api.amazoff.exceptions.AmazonException;
import com.rest_api.amazoff.requestdtos.LoginDto;
import com.rest_api.amazoff.requestdtos.RegisterDto;
import com.rest_api.amazoff.respository.RoleRepository;
import com.rest_api.amazoff.respository.UserRepository;
import com.rest_api.amazoff.security.JwtTokenProvider;
import com.rest_api.amazoff.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String login(LoginDto loginDto) {
		
		//created the authentication manager object
		//authentication for user while logging in validating his username and password
		//authentication refernce will contain authenticated user object
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		//this above authentication reference will contain authenticated user object
		
		//storing authentication manager object in spring security context holder
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateTokn(authentication);
		
		return token;
		
	}

	@Override
	public String register(RegisterDto registerDto) {
		//check for user with this username exists in the database
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new AmazonException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		
		//check for user with this email exists in the database
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new AmazonException(HttpStatus.BAD_REQUEST, "Email already exists");
		}
				
		User user =new User();
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
       Set<Roles> roles = new HashSet<>();//empty role set
       Roles userRole = roleRepository.findByName("ROLE_USER").get();//get ROLE_USER object
       roles.add(userRole);// add it to role set object

       user.setRoles(roles);// set role to user
       
       userRepository.save(user);// save user 
       
       return "User registered succesfully";

	}
	
}
