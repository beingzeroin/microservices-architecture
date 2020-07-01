package com.aditya.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.authentication.dto.LoginResponse;
import com.aditya.authentication.dto.RegisterRequest;
import com.aditya.authentication.entity.UserRecord;
import com.aditya.authentication.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JWTTokenService jwtTokenService;

	public LoginResponse getUserLogin(String username, String password) {

		UserRecord userRecord = userRepository.findByUsernameAndPassword(username, password);
		if (userRecord == null) {
			return null;
		}
		LoginResponse response = new LoginResponse();
		response.setJwtToken(jwtTokenService.generateToken());
		response.setUsername(username);
		response.setMessage("Hello!!");
		return response;
	}

	public boolean isNewUser(RegisterRequest req) {

		UserRecord userRecord = userRepository.findByUsernameAndEmail(req.getUsername(), req.getEmail());

		if (userRecord != null) {
			return false;
		}
		return true;
	}

	public void createUser(RegisterRequest req) {

		UserRecord userRecord = new UserRecord();
		userRecord.setEmail(req.getEmail());
		userRecord.setUsername(req.getUsername());
		userRecord.setPassword(req.getPassword());
		userRepository.save(userRecord);

	}

}
