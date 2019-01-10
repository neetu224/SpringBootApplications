package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	private String jwtSecret;
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {
		UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
		Date date = new Date();
		Date expDate = new Date(date.getTime() + jwtExpirationInMs);
		return Jwts.builder().setSubject(Long.toString(principle.getId())).setIssuedAt(date).setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	public Long getUserIdFromJwt(String token) {
	Claims claims=	Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	return Long.parseLong(claims.getSubject());
	}
	
	public Boolean validateToken(String authToken) {
		try {
		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		return true;
		}
		catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
	}
}
