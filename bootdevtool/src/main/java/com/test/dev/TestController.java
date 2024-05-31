package com.test.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String Test() {
		int a = 617;
		int b = 8191;
		return "This is just Testing sum of a and b is " + (a + b);
	}
}
