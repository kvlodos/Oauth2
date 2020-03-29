package com.example.oauth.custom.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.custom.model.AccessToken;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String>{

	List<AccessToken> findByClientID(String clientId);

	List<AccessToken> findByClientIDAndUserName(String clientId, String username);

	Optional<AccessToken> findByTokenID(String tokenId);

	Optional<AccessToken> findByRefreshToken(String refreshToken);

	Optional<AccessToken> findByAuthenticationID(String authenticationId);
	
}
