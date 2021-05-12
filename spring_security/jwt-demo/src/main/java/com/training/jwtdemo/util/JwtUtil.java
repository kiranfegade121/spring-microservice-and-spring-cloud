package com.training.jwtdemo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "assnbhfjhbwejhbdsfsnfdsfds";
		
	public String generateToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
		                     .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
		                     .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public boolean validateToken(String jwtToken, UserDetails user) {
		String username = extractUsername(jwtToken);
		return username.equals(user.getUsername()) && !isTokenExpired(jwtToken);
	}
	
	public String extractUsername(String jwtToken) {
	     return extractClaim(jwtToken, Claims :: getSubject);   	
	}
	
	public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimResolver.apply(claims);
	}
	
	public Date extractExpirationTime(String jwtToken) {
		 return extractClaim(jwtToken, Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String jwtToken) {
		return extractExpirationTime(jwtToken).before(new Date());
	}
	
	public Claims extractAllClaims(String jwtToken) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
	}
}

















