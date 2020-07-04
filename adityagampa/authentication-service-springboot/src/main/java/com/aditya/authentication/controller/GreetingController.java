package com.aditya.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.authentication.dto.Greeting;
import com.aditya.authentication.service.JWTTokenService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@RestController
public class GreetingController {

	@Autowired
	JWTTokenService jwtTokenService;

	@GetMapping("/api/v1/greeting/public")
	public Greeting welcomeGreeting() {
		return new Greeting("Welcome to API");
	}

	@GetMapping("/api/v1/greeting/protected")
	public Greeting securedWelcomeGreeting(@RequestHeader("Authorization") String token) {

		try {
			jwtTokenService.decode(token);
			return new Greeting("Welcome to authenticated API");
		} catch (JWTDecodeException jde) {
			return new Greeting("You need to be Authenticated first");
		} catch (JWTVerificationException jve) {
			return new Greeting("You need to be Authenticated first");
		}
	}
}
