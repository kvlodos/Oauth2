package com.profile.resource;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.oauth.commons.model.Profile;
import com.profile.reposistory.ProfileReposistory;
import com.profile.service.ProfileService;

@Path("profiles")
public class ProfilesResource {

	@Autowired
//	@Qualifier("profileReposistory")
	private ProfileService profileService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfiles() {
		List<Profile> profiles = profileService.getProfiles();
		return Response.ok().entity(profiles).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfile(@BeanParam Profile profile) {
		Profile profile2 = profileService.addProfile(profile);
		return Response.status(Status.CREATED.getStatusCode()).entity(profile2).build();
	}
	
	@Path("{id}")
	public ProfileResource getProfileResource(@PathParam("id") int id) throws Exception {
		try {
			ProfileResource profileResource = new ProfileResource();
			profileResource.initiate(id);
			return profileResource;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
