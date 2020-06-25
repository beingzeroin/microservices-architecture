package com.aditya.authentication.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class JWTTokenService {

	private final String key = "MY_JWT_TOKEN";
	private final String issuer = "auth0";

	public void decode(String token) throws JWTDecodeException, JWTVerificationException {
		Algorithm algorithm = Algorithm.HMAC256(key);
		DecodedJWT jwt = JWT.decode(token);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
		verifier.verify(token);
	}

	public String generateToken() {
		Algorithm algorithm = Algorithm.HMAC256(key);
		String token = JWT.create().withIssuer(issuer).sign(algorithm);
		return token;
	}

}
