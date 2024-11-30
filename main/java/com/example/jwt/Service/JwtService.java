package com.example.jwt.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	public String generateToken(String username) {
		
		return Jwts.builder()
				.setClaims(new HashMap<>())
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*3))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode("1458asdgadhsa34adgwartgsrtgwsrtfgsrtfgsfgstwghsgh");//generateSecretKey()
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

//	private String generateSecretKey() {
//		KeyGenerator kG;
//		try {
//			kG = KeyGenerator.getInstance("HmacSha256");
//			SecretKey sK  = kG.generateKey();
//			return Base64.getEncoder().encodeToString(sK.getEncoded());
//		} catch (NoSuchAlgorithmException e) {
//			return null;
//		}
//	}

	public String extractUserName(String token) {
		return extractClaim (token,Claims::getSubject);
	}
	
	private <T>  T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build().parseClaimsJws(token)
				.getBody();
	}	

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

}
