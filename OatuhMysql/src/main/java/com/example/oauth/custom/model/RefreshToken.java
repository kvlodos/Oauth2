package com.example.oauth.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Entity
@Table(name = "oauth_refresh_token")
public class RefreshToken {

	@Id
	@Column(name = "token_id")
	private String tokenID;
	
	@Column(name = "token")
	private byte[] token;
	
	@Column(name = "authentication")
	private byte[] authentication;
	
	public RefreshToken() {
	}

	public String getTokenID() {
		return tokenID;
	}

	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}

	public byte[] getToken() {
		return token;
	}
	
	public OAuth2RefreshToken getRefreshToken() {
		if(token != null) {
			return SerializationUtils.deserialize(token);
		}
		
		return null;
	}

	public void setToken(OAuth2RefreshToken token) {
		this.token = SerializationUtils.serialize(token);
	}

	public OAuth2Authentication getAuthentication() {
		return (OAuth2Authentication) SerializationUtils.deserialize(authentication);
	}

	public void setAuthentication(OAuth2Authentication authentication) {
		 this.authentication = SerializationUtils.serialize(authentication);
	}
	
}
