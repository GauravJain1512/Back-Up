package com.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2.model.Alien;
import com.h2.repo.AlientRepo;

@Controller
public class AlienController {
	
	@Autowired
	private AlientRepo alientRepo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/add")
	public String addAlien(Alien alien) {
		Alien save = this.alientRepo.save(alien);
		return "home.jsp";
	}

}
