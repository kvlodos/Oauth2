package com.example.oauth;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableOAuth2Sso
@ApplicationPath("ui")
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		packages("com.example.oauth");
	}
	
}
