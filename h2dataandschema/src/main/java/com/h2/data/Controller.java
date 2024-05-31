package com.h2.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/")
	public List<Person> getPersons(){
		List<Person> list = this.personRepository.findAll();
		return list;
	}
}
