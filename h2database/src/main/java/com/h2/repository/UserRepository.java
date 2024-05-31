package com.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
