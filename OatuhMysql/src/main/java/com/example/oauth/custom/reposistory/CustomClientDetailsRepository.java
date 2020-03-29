package com.example.oauth.custom.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.custom.model.CustomClientDetails;

public interface CustomClientDetailsRepository extends JpaRepository<CustomClientDetails, String>{

	CustomClientDetails findByClientId(String clientId);
	
//	List<CustomClientDetails> findByClient_id(String client_id);
	
	
	
}
