package com.example.oauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.oauth.model.AuthUserDetails;
import com.example.oauth.model.User;
import com.example.oauth.reposistory.UserDetailsReposistory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsReposistory userDetailsReposistory;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<User> user = userDetailsReposistory.findByUsername(name);
		user.orElseThrow(() -> new UsernameNotFoundException("User and password mismatch"));
		UserDetails userDetails = new AuthUserDetails(user.get());
		new AccountStatusUserDetailsChecker().check(userDetails);
		return userDetails;
	}

}
