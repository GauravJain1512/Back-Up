package com.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.models.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);
}
