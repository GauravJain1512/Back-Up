package com.practice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model m) {
		System.out.println("Inside about handler..");
		m.addAttribute("name","Gaurav Jain");
		m.addAttribute("cuttentDate",new Date().toLocaleString());
		return "about";
	}
	
	@RequestMapping(value = "/example-loop", method = RequestMethod.GET)
	public String iterateHandler(Model m) {
		List<String> list = List.of("Ankit","Laxmi","John","Karan");
		m.addAttribute("list",list);
		return "iterate";
	}
	
	@GetMapping("/condition")
	public String conditionHandler(Model model) {
		System.out.println("conditional handler executed...");
		model.addAttribute("isActive",false);
		model.addAttribute("gender","F");
		List<Integer> mylist = List.of(23);
		model.addAttribute("mylist",mylist);
		
		
		return "condition";
	}
	// include fragment
	@GetMapping("/service")
	public String servicesHandler(Model model) {
		model.addAttribute("title","I like to eat Samosa");
		model.addAttribute("subtitle",LocalDateTime.now().toString());
		return "service";
	}
	@GetMapping("/newabout")
	public String newAbout(){
		return "aboutnew";
	}
	
	@GetMapping("/contact")
	public String contact(){
		return "contact";
	}
	
	
}
