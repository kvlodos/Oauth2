package com.example.oauth.custom.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.custom.model.AuthorizationCode;
import java.lang.String;
import java.util.List;

public interface AuthorizationCodeReposistory extends JpaRepository<AuthorizationCode, Integer>{

	List<AuthorizationCode> findByCode(String code);
	
}
