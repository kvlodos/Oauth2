package com.profile.resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.oauth.commons.model.Profile;
import com.profile.service.ProfileService;

//@Path("profiles/{id}")
public class ProfileResource {

	
	@Autowired
	ProfileService profileService;
	
	private Profile profile;
	
	public ProfileResource() {
		this.profile = null;
	}
	
	public ProfileResource(Profile profile) {
		this.profile = profile;
	}

	public void initiate(int id) throws Exception {
		Optional<Profile> optional = profileService.getProfile(id);
		optional.orElseThrow(() -> new Exception("Profile is not found"));
		this.profile = optional.get();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile() {
		return Response.ok().entity(profile).build();
	}
	
//	ProfileReposistory profileReposistory;
//	
//	private Profile profile;
//	
//	public ProfileResource() {
//	}
//	
//	public ProfileResource(Profile profile, ProfileReposistory profileReposistory) {
//		this.profile = profile;
//		this.profileReposistory = profileReposistory;
//	}
//	
////	public ProfileResource(int id) {
////		this.id = id;
////	}
//
//	public ProfileResource getInstance(int id, ProfileReposistory profileReposistory) throws Exception {
//		System.out.println("LOD LOD LOD LOD " + id);
//		Optional<Profile> profile = profileReposistory.findById(id);
//		profile.orElseThrow(() -> new Exception("Profile not found"));
//		return new ProfileResource(profile.get(), profileReposistory);
//	}
//	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getProfile() throws Exception {
////		Optional<Profile> profile = profileReposistory.findById(id);
////		profile.orElseThrow(() -> new Exception("Profile not found"));
////		return Response.ok().entity(profile.get()).build();
//		
//		
//		return Response.ok().entity(profile).build();
//	}
	
}
