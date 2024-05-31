package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.UserDto;

public interface UserService {
	
	public UserDto registerNewUser(UserDto user);

	public UserDto createUser(UserDto user); 
	
	public UserDto updateUser(UserDto user, Integer userId);
	
	public UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
