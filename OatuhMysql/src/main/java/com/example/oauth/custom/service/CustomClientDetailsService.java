package com.example.oauth.custom.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.example.oauth.custom.model.CustomClientDetails;
import com.example.oauth.custom.reposistory.CustomClientDetailsRepository;

@Service
public class CustomClientDetailsService implements ClientDetailsService, ClientRegistrationService {

	@Autowired
	private CustomClientDetailsRepository customClientDetailsRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		try {
			System.out.println("LOD LOD LOD");
			CustomClientDetails client = customClientDetailsRepository.findByClientId(clientId);
			
//			String resourceIds = client.getResourceIds();
//			String scopes = client.getScopes();
//			String grantTypes = client.getAuthorizedGrantTypes();
//			String authorities = client.getAuthorities();
			
			BaseClientDetails base = convertFromCustomClientDetails(client);
//			base.setClientSecret(client.getClientSecret());
//			base.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
//			base.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
//			base.setAdditionalInformation(new HashMap<String, Object>());
//			base.setRegisteredRedirectUri(new HashSet<String>(Arrays.asList(new String[] {"http://localhost:8183/login"})));
//			base.setAutoApproveScopes(new ArrayList<String>(Arrays.asList(client.getScopes().split(","))));
			return base;
		} catch (Exception e) {
			System.out.println("LOD LOD LOD");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
		CustomClientDetails customClientDetails = converToCustomClientDetails(clientDetails);
		customClientDetailsRepository.save(customClientDetails);
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
	}

	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
	}

	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		customClientDetailsRepository.deleteById(clientId);		
	}

	@Override
	public List<ClientDetails> listClientDetails() {
		List<CustomClientDetails>  clients = customClientDetailsRepository.findAll();
		List<ClientDetails> clientDetails = new ArrayList<ClientDetails>();
		clients.forEach(client -> {
			clientDetails.add(convertFromCustomClientDetails(client));
		});
		
		return clientDetails;
	}
	
	private BaseClientDetails convertFromCustomClientDetails(CustomClientDetails client) {
		String resourceIds = client.getResourceIds();
		String scopes = client.getScopes();
		String grantTypes = client.getAuthorizedGrantTypes();
		String authorities = client.getAuthorities();
		
		BaseClientDetails base = new BaseClientDetails(client.getClientId(), resourceIds, scopes, grantTypes, authorities);
		base.setClientSecret(client.getClientSecret());
		base.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
		base.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
		base.setAdditionalInformation(new HashMap<String, Object>());
		base.setRegisteredRedirectUri(new HashSet<String>(Arrays.asList(new String[] {"http://localhost:8183/login"})));
		base.setAutoApproveScopes(new ArrayList<String>(Arrays.asList(client.getScopes().split(","))));
		return base;
	}
	
	private CustomClientDetails converToCustomClientDetails(ClientDetails clientDetails) {
		
		return new CustomClientDetails(clientDetails.getClientId(), clientDetails.getClientSecret(), clientDetails.getRegisteredRedirectUri().stream().collect(Collectors.joining(",")), clientDetails.getScope().stream().collect(Collectors.joining(",")), clientDetails.getAccessTokenValiditySeconds(), clientDetails.getRefreshTokenValiditySeconds(), clientDetails.getResourceIds().stream().collect(Collectors.joining(",")), clientDetails.getAuthorizedGrantTypes().stream().collect(Collectors.joining(",")), null, null);
	}

}
