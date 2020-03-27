package com.example.oauth;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("ui")
public class ApplicationConfig extends ResourceConfig{

	public ApplicationConfig() {
		packages("com.example.oauth");
	}
	
}
