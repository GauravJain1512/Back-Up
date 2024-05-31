package com.blog.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.UserDto;
import com.blog.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	// update user
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer userId){
		
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updateUser);
	}
	//ADMIN
	// Delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer usedId){
		this.userService.deleteUser(usedId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
	}
	
	// get All user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// get user
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer userId){
		UserDto userDto = this.userService.getUserById(userId);
		
		return ResponseEntity.ok(userDto);
	}
	
}



