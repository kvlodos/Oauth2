package com.profile.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oauth.commons.model.Profile;
import com.profile.reposistory.ProfileReposistory;
import com.profile.service.ProfileService;

@Service
@Scope("prototype")
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileReposistory profileReposistory ;
	
	@Override
	public List<Profile> getProfiles() {
		return profileReposistory.findAll();
	}
	
	@Override
	public Profile addProfile(Profile profile) {
		return profileReposistory.save(profile);
	}
	
	@Override
	public Optional<Profile> getProfile(int id) {
		return profileReposistory.findById(id);
	}
	
}
