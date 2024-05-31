package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springsecurity.models.User;
import com.springsecurity.repository.UserRepository;

@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	
	
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("jndhananjay2@gmail.com");
		user.setUsername("dhananjay");
		user.setPassword(this.bCryptPasswordEncoder.encode("dhananjay"));
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);
		
		User user1 = new User();
		user1.setEmail("jngaurav2@gmail.com");
		user1.setUsername("gaurav");
		user1.setPassword(this.bCryptPasswordEncoder.encode("gaurav"));
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
		
	}

}
