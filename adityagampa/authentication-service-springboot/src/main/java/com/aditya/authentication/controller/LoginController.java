package com.aditya.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.authentication.dto.LoginRequest;
import com.aditya.authentication.dto.LoginResponse;
import com.aditya.authentication.service.JWTTokenService;
import com.aditya.authentication.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userSvc;

	@Autowired
	JWTTokenService jwtTokenService;

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@PostMapping("/api/v1/login")
	public LoginResponse userLogin(@RequestBody LoginRequest req) {
		return userSvc.getUserLogin(req.getUsername(), req.getPassword());
	}

}
