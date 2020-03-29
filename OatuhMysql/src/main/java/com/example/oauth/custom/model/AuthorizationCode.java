package com.example.oauth.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Entity
@Table(name = "authorization_code")
public class AuthorizationCode {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "authentication")
	private byte[] authentication;

	public AuthorizationCode() {
	}
	
	public AuthorizationCode(String code, byte[] authentication) {
		this.code = code;
		this.authentication = authentication;
	}
	
	public AuthorizationCode(String code, OAuth2Authentication authentication) {
		this.code = code;
		this.authentication = SerializationUtils.serialize(authentication);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
	
	public OAuth2Authentication getOauthAuthentication() {
		return SerializationUtils.deserialize(authentication);
	}
	
	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = SerializationUtils.serialize(authentication);
	}
}
