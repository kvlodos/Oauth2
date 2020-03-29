/**
 * 
 */
package com.example.oauth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

	@RequestMapping("/")
	public String getHome() {
		return "home.html";
	}
	
	@RequestMapping("/secure")
	public String secure(HttpServletRequest request) {
		System.out.println(request.getUserPrincipal());
		return "secure.html";
	}
	
	
	
}
