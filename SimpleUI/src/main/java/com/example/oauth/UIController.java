package com.example.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;

@Controller
@Path("/")
//@EnableOAuth2Sso
public class UIController {

	@Path("home")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public InputStream getHome() throws FileNotFoundException {
		File file = new File("/home/local/ZOHOCORP/venkat-3093/Desktop/home.html");
		return new FileInputStream(file);
	}
	
	
}
