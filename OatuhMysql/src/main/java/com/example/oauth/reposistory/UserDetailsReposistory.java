package com.example.oauth.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth.model.User;

public interface UserDetailsReposistory extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String name);
	
}
