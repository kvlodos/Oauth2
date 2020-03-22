package com.profile.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("profile/v1")
public class JerseyApplicationConfig extends ResourceConfig{

	public JerseyApplicationConfig() {
		packages("com.profile.resource");
	}
	
}
