package com.profile.service;

import java.util.List;
import java.util.Optional;

import com.oauth.commons.model.Profile;

public interface ProfileService {

	public List<Profile> getProfiles();
	
	public Profile addProfile(Profile profile);
	
	public Optional<Profile> getProfile(int id);
	
}
