package com.example.studySphere.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.studySphere.authentication.ExcludePathMatcher;
import com.example.studySphere.authentication.jwt.JWTTokenVerifier;
import com.example.studySphere.authentication.jwtValidation.JWTTokenAuthenticationFilter;
import com.example.studySphere.authentication.jwtValidation.JWTTokenAuthenticationProvider;
import com.example.studySphere.authentication.login.AuthUserDetailsService;
import com.example.studySphere.authentication.login.MyUsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {

	@Autowired
	public void configureProvider(AuthenticationManagerBuilder auth,
			AuthUserDetailsService authUserDetailsService, JWTTokenVerifier jwtTokenVerifier)
			throws Exception {
		//
		JWTTokenAuthenticationProvider jwtTokenAuthenticationProvider =
				new JWTTokenAuthenticationProvider(jwtTokenVerifier);
		auth.authenticationProvider(jwtTokenAuthenticationProvider);

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(authUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(8));
		auth.authenticationProvider(daoAuthenticationProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		//
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/api/login").permitAll()
				.anyRequest().authenticated());

		AuthenticationManager authenticationManager =
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

		http.addFilter(new MyUsernamePasswordAuthenticationFilter(authenticationManager));
		http.addFilterBefore(new JWTTokenAuthenticationFilter(
				new ExcludePathMatcher(new String[] {"/", "/api/login", "/error"}),
				authenticationManager), MyUsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();

		return http.build();
	}
}
