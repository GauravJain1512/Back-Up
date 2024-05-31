package com.blog.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.exceptions.ApiException;
import com.blog.api.payloads.JwtAuthRequest;
import com.blog.api.payloads.JwtAuthResponse;
import com.blog.api.payloads.UserDto;
import com.blog.api.security.JWTTokenHelper;
import com.blog.api.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
		
		this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
		try { 
			
		this.authenticationManager.authenticate(authenticationToken);
		
		}catch (BadCredentialsException e) {
			
			System.out.println("Invalid Details !!");
			throw new ApiException("Invalid Username or Password!!");
		
		}
		
	}
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto newUserDto = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(newUserDto,HttpStatus.CREATED);
	}
}






