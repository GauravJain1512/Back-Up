package com.springsecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springsecurity.models.User;
@Service
public class UserService {
	
	List<User> list = new ArrayList<>();

	public UserService() {
		list.add(new User("dhananjay","adsd","jndhananjay2@gmail.com"));
		list.add(new User("gaurav","addsdsd","jngaurav2@gmail.com"));
	}
	
	// get all user
	public List<User> getAllUsers(){
		return this.list;
	}

	//get single user
	public User getUser(String username){
		return this.list.stream().filter((user)->user.getUsername().equals(username)).findAny().orElse(null);
	}
	// add user
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}
}
