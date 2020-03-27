/**
 * 
 */
package com.example.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

	@RequestMapping("/")
	public String getHome() {
		return "home.html";
	}
	
	@RequestMapping("/secure")
	public String secure() {
		return "secure.html";
	}
	
	
	
}
