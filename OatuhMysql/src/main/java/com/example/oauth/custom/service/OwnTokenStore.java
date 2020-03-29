package com.example.oauth.custom.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.example.oauth.custom.model.AccessToken;
import com.example.oauth.custom.model.RefreshToken;
import com.example.oauth.custom.reposistory.AccessTokenRepository;
import com.example.oauth.custom.reposistory.RefreshTokenRepository;

public class OwnTokenStore implements TokenStore{

	private AccessTokenRepository accessTokenRepository;

    private RefreshTokenRepository refreshTokenRepository;
    
    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
	
	public OwnTokenStore(AccessTokenRepository accessTokenRepository, RefreshTokenRepository refreshTokenRepository) {
		this.accessTokenRepository = accessTokenRepository;
		this.refreshTokenRepository = refreshTokenRepository;
	}
	
	private String extractTokenKey(String value) {
        if(value == null) {
            return null;
        } else {
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var5) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
            }

            try {
                byte[] e = digest.digest(value.getBytes("UTF-8"));
                return String.format("%032x", new Object[]{new BigInteger(1, e)});
            } catch (UnsupportedEncodingException var4) {
                throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
            }
        }
    }

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		Optional<AccessToken> optional = accessTokenRepository.findByTokenID(extractTokenKey(token));
		if(optional.isPresent()) {
			return optional.get().getAuthentication();
		}
		
		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        if (readAccessToken(token.getValue()) != null) {
            this.removeAccessToken(token);
        }

        AccessToken accessToken =  new AccessToken();
        accessToken.setTokenID(extractTokenKey(token.getValue())); 
        accessToken.setToken(token);
        accessToken.setAuthenticationID(authenticationKeyGenerator.extractKey(authentication));
        accessToken.setUserName(authentication.isClientOnly() ? null : authentication.getName());
        accessToken.setClientID(authentication.getOAuth2Request().getClientId());
        accessToken.setAuthentication(authentication);
        accessToken.setRefreshToken(extractTokenKey(refreshToken));

        accessTokenRepository.save(accessToken);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		 Optional<AccessToken> accessToken = accessTokenRepository.findByTokenID(extractTokenKey(tokenValue));
	        if (accessToken.isPresent()) {
	            return accessToken.get().getToken();
	        }
	        return null;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		Optional<AccessToken> accessToken = accessTokenRepository.findByTokenID(extractTokenKey(token.getValue()));
        if (accessToken.isPresent()) {
            accessTokenRepository.delete(accessToken.get());
        }
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		RefreshToken refToken = new RefreshToken();
		refToken.setTokenID(extractTokenKey(refreshToken.getValue()));
		refToken.setToken(refreshToken);
		refToken.setAuthentication(authentication);
		refreshTokenRepository.save(refToken);
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByTokenID(extractTokenKey(tokenValue));
		if(optional.isPresent()) {
			return optional.get().getRefreshToken();
		}
        return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByTokenID(extractTokenKey(token.getValue()));
		if(optional.isPresent()) {
			return optional.get().getAuthentication();
		}
        return null;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByTokenID(extractTokenKey(token.getValue()));
        if (optional.isPresent()) {
            refreshTokenRepository.delete(optional.get());
        }
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		Optional<AccessToken> token = accessTokenRepository.findByRefreshToken(extractTokenKey(refreshToken.getValue()));
        if(token.isPresent()){
            accessTokenRepository.delete(token.get());
        }
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken accessToken = null;
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        Optional<AccessToken> token = accessTokenRepository.findByAuthenticationID(authenticationId);

        if(token.isPresent()) {
            accessToken = token.get().getToken();
            if(accessToken != null && !authenticationId.equals(this.authenticationKeyGenerator.extractKey(this.readAuthentication(accessToken)))) {
//            	Keep the store consistent (maybe the same user is represented by this authentication but the details have changed)
                this.removeAccessToken(accessToken);
                this.storeAccessToken(accessToken, authentication);
            }
        }
        return accessToken;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		Collection<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>();
        List<AccessToken> result = accessTokenRepository.findByClientIDAndUserName(clientId, userName);
        result.forEach(e-> tokens.add(e.getToken()));
        return tokens;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		Collection<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>();
        List<AccessToken> result = accessTokenRepository.findByClientID(clientId);
        result.forEach(e-> tokens.add(e.getToken()));
        return tokens;
	}

}
