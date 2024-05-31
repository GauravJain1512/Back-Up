package com.h2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.model.User;
import com.h2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		User saveUser = this.userRepository.save(user);
		return saveUser;
	}
	
	public List<User> createUsers(List<User> users) {
		List<User> saveAll = this.userRepository.saveAll(users);
		return saveAll;
	}
	
	public User getUserById(Integer id) {
		Optional<User> optional = this.userRepository.findById(id);
		User user = optional.get();
		return user;
	}
	
	public List<User> getAllUsers(){
		List<User> list = this.userRepository.findAll();
		return list;
	}
	
	public User updateUser(Integer id, User user) {
		Optional<User> optional = this.userRepository.findById(id);
		User user2 = optional.get();
		user2.setName(user.getName());
		user2.setAddress(user.getAddress());
		User updatedUser = this.userRepository.save(user2);
		return updatedUser;
	}
	
	public void deleteUser(Integer id) {
		this.userRepository.deleteById(id);
	}
	
}




