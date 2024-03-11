package com.rest_api.amazoff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest_api.amazoff.security.JwtAuthenticationFilter;
import com.rest_api.amazoff.security.JwtAuthenticatonEntryPoint;

//7th step configuring JWT in spring security
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired//new
	private JwtAuthenticatonEntryPoint jwtAuthenticatonEntryPoint;
	
	@Autowired//new
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		  return authenticationConfiguration.getAuthenticationManager();
	  }
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	      http.csrf(AbstractHttpConfigurer::disable)
//	      http.csrf().disable()
	      .authorizeHttpRequests(authorize -> 
	    	   
	      authorize.requestMatchers(HttpMethod.GET, "/delivery_partner/**").hasAnyRole("ADMIN", "MANAGER", "USER")
	    	        .requestMatchers(HttpMethod.POST, "/delivery_partner/**").hasRole("ADMIN")
	    	        .requestMatchers(HttpMethod.PUT, "/delivery_partner/**").hasRole("MANAGER")
	    	        .requestMatchers(HttpMethod.DELETE, "/delivery_partner/**").hasRole("ADMIN")
	
	    	        .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("ADMIN", "MANAGER", "USER")
	    	        .requestMatchers(HttpMethod.POST, "/orders/**").hasRole("ADMIN")
	    	        .requestMatchers(HttpMethod.PUT, "/orders/**").hasRole("MANAGER")
		        	.requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
		        	
		        	.requestMatchers(HttpMethod.POST, "/auth/login/**").permitAll()
		        	.requestMatchers(HttpMethod.POST, "/auth/signin/**").permitAll()
		        	.requestMatchers(HttpMethod.POST, "/auth/register/**").permitAll()
		        	.requestMatchers(HttpMethod.POST, "/auth/signup/**").permitAll()
	
		        	.anyRequest().authenticated()
	
	    	)
	    	.httpBasic(org.springframework.security.config.Customizer.withDefaults())
	    	.exceptionHandling(exception -> exception 
	    			.authenticationEntryPoint(jwtAuthenticatonEntryPoint))
	    	.sessionManagement(
	    			session -> 
	    			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	      //new
	      http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	      
	      return http.build();
	      
	  }
}
