package com.jpa.test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootjpaexampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootjpaexampleApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user = new User();
//		user.setName("Gaurav Jain");
//		user.setCity("Dhule");
//		user.setStatus("I am Java Programmer");
//		
//		User user2 = userRepository.save(user);
//		System.out.println(user2);
//		
//		// create object of user
//		User user1 = new User();
//		user1.setName("Ram");
//		user1.setCity("Pune");
//		user1.setStatus("Python Programmer");
////		
//		User user2 = new User();
//		user2.setName("Ankit");
//		user2.setCity("Jalgoan");
//		user2.setStatus("C Programmer");
//		// saving single user
		//User resultUser = userRepository.save(user1);
//		//save multiple object
//		List<User> list = List.of(user1,user2);
//		Iterable<User> result = userRepository.saveAll(list);
//		result.forEach(user->{
//			System.out.println(user);
//		});
////		System.out.println("saved user: "+user1);
//		System.out.println("done..");
//		
//		Optional<User> optional = userRepository.findById(5);
//		User user = optional.get();
//		user.setName("Ankit Tiwari");
//		User user2 = userRepository.save(user);
//		System.out.println(user2);
//		System.out.println(user);
//		
//		// getting data
//		Iterable<User> all = userRepository.findAll();
//		Iterator<User> it = all.iterator();
//		while(it.hasNext()) {
//			User user = it.next();
//			System.out.println(user);
//			
//		}
//		all.forEach(new Consumer<User>() {
//
//			@Override
//			public void accept(User t) {
//				System.out.println(t);
//			}
//			
//		});
//		all.forEach(user->{System.out.println(user);});
		
		// deleting the user element
//		userRepository.deleteById(5);
//		System.out.println("deleted...");
//		Iterable<User> all = userRepository.findAll();
//		all.forEach(user->{System.out.println(user);});
//		userRepository.deleteAll(all);
//		List<User> list = userRepository.findByName("Ram");
//		list.forEach(user4->{System.out.println(user4);});
//		}
////	
//		List<User> allUser = userRepository.getAllUser();
//		allUser.forEach(e->{
//			System.out.println(e);
//		});
//		List<User> list = userRepository.getUserByName("Ram");
//		list.forEach(e->{
//			System.out.println(e);
//		});
		
		userRepository.getUsers().forEach(e->{System.out.println(e);});
	}
}

