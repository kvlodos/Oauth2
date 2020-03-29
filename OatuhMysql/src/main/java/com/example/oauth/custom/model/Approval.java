package com.example.oauth.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_approvals")
public class Approval {

	@Column(name = "userId")
	private String userID;
	
	@Column(name = "clientId")
	private String clientID;
	
	@Column(name = "scope")
	private String scope;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "expiresAt")
	private String expiresAt;
	
	@Column(name = "lastModifiedAt")
	private String lastModifiedAt;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(String lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
}
