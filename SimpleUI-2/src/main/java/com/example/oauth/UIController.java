package com.example.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

	@RequestMapping("/")
	public String home() {
		System.out.println("LOD LOD LOD");
		return "home.html";
	}
	
	@RequestMapping("/secure")
	public String secure() {
		return "secure.html";
	}
	
}
