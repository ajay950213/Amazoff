package com.rest_api.amazoff.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.rest_api.amazoff.exceptions.AmazonException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//4th step in JWT Authentication
@Component
public class JwtTokenProvider {

	@Value("${app-jwt-secrete}")
	private String jwtSecrete;

	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpiration;

	// this is to decrypt or decode our encrypted secrete key which is in
	// application.properties file
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecrete));
	}

	// generate JWT token
	public String generateTokn(Authentication authentication) {
		// authentication refernce will conntain authenticated user object
		// by using it we are creating JWT token

		String username = authentication.getName();
		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

		String token = Jwts.builder().subject(username).issuedAt(currentDate).expiration(expireDate).signWith(key())
				.compact();

		return token;
	}

	// we are creating this method to get username from the token which is generated
	// by above method
	// get username for JWT token
	public String getUsername(String token) {

		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();

	}

	// validate JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
			return true;
		} catch (MalformedJwtException malformedJwtException) {

			throw new AmazonException(HttpStatus.BAD_REQUEST, "Invalid Jwt Token");
		} catch (ExpiredJwtException expiredJwtException) {
			throw new AmazonException(HttpStatus.BAD_REQUEST, "Toke Expired");
		} catch (UnsupportedJwtException unsupportedJwtException) {
			throw new AmazonException(HttpStatus.BAD_REQUEST, "Unsupported jwt token");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new AmazonException(HttpStatus.BAD_REQUEST, "Jwt claims string is null or empty");
		}

	}

}
