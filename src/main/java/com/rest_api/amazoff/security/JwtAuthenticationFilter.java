package com.rest_api.amazoff.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//5th step in jwt authentication
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get jwt token from http request
		// We will get JWT Token from reqest header of postman as key value pair
		// we should extract jwt token from request header by using its key ->
		// Authorization

		String token = getTokenFromRequest(request);

		// validate token
		if (org.springframework.util.StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

			// get username from token
			String username = jwtTokenProvider.getUsername(token);

			// load the user who is associated with token
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		}

		filterChain.doFilter(request, response);

	}

	private String getTokenFromRequest(HttpServletRequest request) {

		// this request refernce will have all request details like
		// header, body and status codes, using that request refernce
		// we can extract jwt token using key -> Authorization,
		// this key's value is token

		String bearerToken = request.getHeader("Authorization");

		// from request we will get token alogn with Bearer_ (space) we should extract
		// token from it
		// we want only token

		// token will come along with Bearer+space+token, we want only token we are
		// extracting it
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

			return bearerToken.substring(7, bearerToken.length());
		}
		return null;

	}

}
