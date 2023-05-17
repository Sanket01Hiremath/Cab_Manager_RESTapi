package com.application.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{
	public Optional<Users> findUsersByPhone(String phone);
}
