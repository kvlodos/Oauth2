package com.example.oauth.custom.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class CustomClientDetails {

	@Id
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "web_server_redirect_uri")
	private String redirectUris;
	
	@Column(name = "scope")
	private String scopes;
	
	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;
	
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;
	
	@Column(name = "resource_ids")
	private String resourceIds;
	
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name = "authorities")
	private String authorities;
	
//	@Column(name = "additional_information")
//	private HashMap<String, Object> additionalInformation = new HashMap<String, Object>();
	
	@Column(name = "autoapprove")
	private Boolean  autoApprove;
	
//	private boolean secretRequired;
//	
//	private boolean scoped;
	
	public CustomClientDetails() {
	}
	
	

	public CustomClientDetails(String clientId, String clientSecret, String redirectUris, String scopes,
			Integer accessTokenValidity, Integer refreshTokenValidity, String resourceIds, String authorizedGrantTypes,
			String authorities, Boolean autoApprove) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUris = redirectUris;
		this.scopes = scopes;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.resourceIds = resourceIds;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.authorities = authorities;
		this.autoApprove = autoApprove;
	}



	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(String redirectUris) {
		this.redirectUris = redirectUris;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

//	public HashMap<String, Object> getAdditionalInformation() {
//		return additionalInformation;
//	}
//
//	public void setAdditionalInformation(HashMap<String, Object> additionalInformation) {
//		this.additionalInformation = additionalInformation;
//	}

	public boolean isAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(boolean autoApprove) {
		this.autoApprove = autoApprove;
	}

//	public boolean isSecretRequired() {
//		return secretRequired;
//	}
//
//	public void setSecretRequired(boolean secretRequired) {
//		this.secretRequired = secretRequired;
//	}
//
//	public boolean isScoped() {
//		return scoped;
//	}
//
//	public void setScoped(boolean scoped) {
//		this.scoped = scoped;
//	}

}
