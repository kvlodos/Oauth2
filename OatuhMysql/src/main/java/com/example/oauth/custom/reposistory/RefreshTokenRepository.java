/**
 * 
 */
package com.example.oauth.custom.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.custom.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

	Optional<RefreshToken> findByTokenID(String tokenid);
	
}
