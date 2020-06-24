package com.aditya.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.authentication.dto.Greeting;

@RestController
public class GreetingController {

	@GetMapping("/api/v1/greeting/public")
	public Greeting welcomeGreeting() {
		return new Greeting("Welcome to API");
	}

	@GetMapping("/api/v1/greeting/protected")
	public Greeting securedWelcomeGreeting(@RequestHeader("Authorization") String token) {
		if (token.contains("123")) {
			return new Greeting("Welcome to authenticated API");
		}
		return new Greeting("You need to be Authenticated first");
	}

}
