package com.aditya.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.authentication.entity.UserRecord;

@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer> {
	UserRecord findByUsernameAndPassword(String username, String password);

	UserRecord findByUsernameAndEmail(String username, String email);
}
