package com.example.oauth.custom.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import com.example.oauth.custom.model.AuthorizationCode;
import com.example.oauth.custom.reposistory.AuthorizationCodeReposistory;

public class OwnAuthorizationCodeServices implements AuthorizationCodeServices {

	@Autowired
	AuthorizationCodeReposistory authorizationCodeReposistory;
	
	private Random random = new SecureRandom();
	
	private int codeLength = 25;
	private static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public AuthorizationCode authorizationCode(String code) {
		List<AuthorizationCode> authorizationCodes = authorizationCodeReposistory.findByCode(code);
		for(AuthorizationCode authorizationCode: authorizationCodes) {
			if(authorizationCode.getCode().equals(code)) {
				return authorizationCode;
			}
		}
		return null;
	}
	
	public void store(AuthorizationCode authorizationCode) {
		authorizationCodeReposistory.save(authorizationCode);
	}
	
	@Override
	public String createAuthorizationCode(OAuth2Authentication authentication) {
		String code = generate();
		AuthorizationCode authorizationCode = new AuthorizationCode(code, authentication);
		store(authorizationCode);
		return code;
	}

	@Override
	public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
		AuthorizationCode authorizationCode = authorizationCode(code);
		if(authorizationCode == null) {
			throw new InvalidGrantException("Invalid authorization code: " + code);
		}
		
		authorizationCodeReposistory.deleteById(authorizationCode.getId());
		return authorizationCode.getOauthAuthentication();
	}

	public String generate() {
		byte[] verifierBytes = new byte[codeLength];
		random.nextBytes(verifierBytes);
		return getAuthorizationCodeString(verifierBytes);
	}
	
	protected String getAuthorizationCodeString(byte[] verifierBytes) {
		char[] chars = new char[verifierBytes.length];
		for (int i = 0; i < verifierBytes.length; i++) {
			chars[i] = DEFAULT_CODEC[((verifierBytes[i] & 0xFF) % DEFAULT_CODEC.length)];
		}
		return new String(chars);
	}
}
