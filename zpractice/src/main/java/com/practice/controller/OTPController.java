package com.practice.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OTPController {
	Random random = new Random(000);
	
	@GetMapping("/otp")
	@ResponseBody
	public String getOtp() {
		
		int otp = random.nextInt(100,999);
		System.out.println(otp);
		return "The random number is: "+otp;
	}
	
	@PostMapping("/last")
	@ResponseBody
	public String getLastDigit(@RequestParam("s") Integer s) {
	//	int parseInt = Integer.parseInt(s);
		int result = s % 10;
		System.out.println(result);
		return "The last digit of given number is: "+result;
	}

}
