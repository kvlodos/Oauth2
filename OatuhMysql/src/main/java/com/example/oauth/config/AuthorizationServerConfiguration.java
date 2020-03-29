package com.example.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.example.oauth.custom.reposistory.AccessTokenRepository;
import com.example.oauth.custom.reposistory.RefreshTokenRepository;
import com.example.oauth.custom.service.CustomClientDetailsService;
import com.example.oauth.custom.service.OwnAuthorizationCodeServices;
import com.example.oauth.custom.service.OwnTokenStore;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomClientDetailsService customClientDetailsService;
	
	@Autowired
	private AccessTokenRepository accessTokenRepository;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Bean
	protected UserApprovalHandler approvalHandler() {
//		return new JdbcTokenStore(dataSource);
		TokenStoreUserApprovalHandler approvalHandler = new TokenStoreUserApprovalHandler();
		approvalHandler.setTokenStore(tokenStore());
//		approvalHandler.setClientDetailsService(customClientDetailsService);
		OAuth2RequestFactory oAuth2RequestFactory = new DefaultOAuth2RequestFactory(customClientDetailsService);
		approvalHandler.setRequestFactory(oAuth2RequestFactory);
		return approvalHandler;
	}
	
	@Bean
	protected TokenStore tokenStore() {
//		return new JdbcTokenStore(dataSource);
		
		return new OwnTokenStore(accessTokenRepository, refreshTokenRepository);
	}
	
//	@Bean
//	protected AuthorizationCodeServices authorizationCodeServices() {
//		return new JdbcAuthorizationCodeServices(dataSource);
//	}
	
	@Bean
	protected ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}
	
	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new OwnAuthorizationCodeServices();
	}
	
//	@Bean
//	protected ApprovalStore approvalStore() {
//		return new JdbcApprovalStore(dataSource);
//	}
	
	@Autowired
	AuthenticationManager authenticationManager; 
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.allowFormAuthenticationForClients();
		security.checkTokenAccess("permitAll()").tokenKeyAccess("permitAll()");
//		security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')").tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
		
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(customClientDetailsService);
//		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.tokenStore(tokenStore());
		endpoints.authorizationCodeServices(authorizationCodeServices());
//		endpoints.userApprovalHandler(approvalHandler());
		endpoints.approvalStore(approvalStore());
//		endpoints.pathMapping("/oauth/token", "/oauth/token");
	}

}
