package com.example.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Path("/home")
public class HomeController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String home() throws FileNotFoundException {
//		File file = new File("/home/local/ZOHOCORP/venkat-3093/Desktop/home.html");
//		return new FileInputStream(file);
		return "Welcome";
	}
}
