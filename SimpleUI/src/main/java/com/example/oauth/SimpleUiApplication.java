package com.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
//@EnableResourceServer
public class SimpleUiApplication{

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("")
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleUiApplication.class, args);
	}

}
