package com.example.oauth.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Entity
@Table(name = "oauth_access_token")
public class AccessToken {


	@Id
	@Column(name = "authentication_id")
	private String authenticationID;
	
	@Column(name = "token_id")
	private String tokenID;
	
	@Column(name = "token")
	private byte[] token;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "client_id")
	private String clientID;
	
	@Column(name = "authentication")
	private byte[] authentication;
	
	@Column(name = "refresh_token")
	private String refreshToken;

	public AccessToken() {
	}
	
	public String getAuthenticationID() {
		return authenticationID;
	}

	public void setAuthenticationID(String authenticationID) {
		this.authenticationID = authenticationID;
	}

	public String getTokenID() {
		return tokenID;
	}

	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}

	public OAuth2AccessToken getToken() {
		return (OAuth2AccessToken) SerializationUtils.deserialize(token);
	}

	public void setToken(OAuth2AccessToken token) {
		this.token = SerializationUtils.serialize(token);
	}
	
//	public void setToken(String token) {
//		this.token = SerializableObjectConverter.serialize(token);
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public OAuth2Authentication getAuthentication() {
		return (OAuth2Authentication) SerializationUtils.deserialize(authentication);
	}

	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = SerializationUtils.serialize(authentication);
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
