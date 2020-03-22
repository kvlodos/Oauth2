package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "serverhealth")
public class ServerHealthChecker {

	Map<String, String> map = new HashMap<String, String>();
	
	@ReadOperation
	public Map<String, String> get() {
		map.put("Health", "Okay");
		return map;
	}
	
}
