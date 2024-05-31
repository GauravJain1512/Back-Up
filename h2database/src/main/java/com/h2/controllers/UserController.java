package com.h2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.h2.model.User;
import com.h2.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		User createUser = this.userService.createUser(user);
		return createUser;
	}
	
	@PostMapping("/addUsers")
	public List<User> addAllUser(@RequestBody List<User> users){
		List<User> createUsers = this.userService.createUsers(users);
		return createUsers;
	}
	
	@GetMapping("/users/{id}")
	public User gerUser(@PathVariable("id") Integer id) {
		User user = this.userService.getUserById(id);
		return user;
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> allUsers = this.userService.getAllUsers();
		return allUsers;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
		User updateUser = this.userService.updateUser(id, user);
		return updateUser;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		this.userService.deleteUser(id);
	}
}



