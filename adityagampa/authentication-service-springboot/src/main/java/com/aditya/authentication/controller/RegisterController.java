package com.aditya.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.authentication.dto.LoginResponse;
import com.aditya.authentication.dto.RegisterRequest;
import com.aditya.authentication.service.UserService;

@RestController
public class RegisterController {

	@Autowired
	UserService userSvc;

	@PostMapping("/api/v1/register")
	public LoginResponse registerUser(@RequestBody RegisterRequest req) {

		if (!userSvc.isNewUser(req)) {
			return null;
		}

		userSvc.createUser(req);
		return userSvc.getUserLogin(req.getUsername(), req.getPassword());
	}

}
