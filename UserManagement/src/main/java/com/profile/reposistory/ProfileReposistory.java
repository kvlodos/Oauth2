package com.profile.reposistory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.oauth.commons.model.Profile;

//@Component("profileReposistory")
//@Scope("prototype")
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface ProfileReposistory extends JpaRepository<Profile, Integer> {

}
